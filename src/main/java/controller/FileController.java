package controller;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.DocumentReq;
import domain.response.LoginRes;
import lucene.WordUtil;
import model.Documents;
import model.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.IDocumentService;
import service.ILogService;
import utils.DateUtil;
import utils.FileUtil;
import utils.MyProps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
public class FileController {

    String ACCESS_KEY_ID = "008d46fa7242449ea22e192e76ff8e83";
    String SECRET_ACCESS_KEY = "d86790d96a014880b09b75430d786713";
//    String FILE_PATH = "C:\\Users\\asus\\Desktop\\毕设\\系统\\后端\\design\\src\\main\\resources\\static\\files\\";

     @Autowired
     IDocumentService documentService;
     @Autowired
     MyProps myProps;
     @Autowired
    ILogService iLogService;

    /**
     * 实现文件上传
     * */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultRich fileUpload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return  ResultRich.newInstance(ErrorCode.FILE_EMPTY);
        }

        String fileName = file.getOriginalFilename();
        String outfile = myProps.getFilePath() + DateUtil.getCurrentDateBylong() + "_" + fileName;
        File file1 = new File(outfile);
        FileOutputStream fos = null;
        byte[] b = new byte[1024];
        InputStream is = null;

        try {
            fos = new FileOutputStream(outfile);
            is = file.getInputStream();
            int len;
            while((len = is.read(b,0,b.length))!=-1){
                fos.write(b,0,len);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        DocClient client = new DocClient(config);
        FileUtil fileUtil = new FileUtil();
        String documentTag = fileUtil.createDocument(client, file1, file.getOriginalFilename(), "doc", null, null, null);
//        f.deleteOnExit();
        /*
        提取关键字
         */
        String content = WordUtil.readWord(outfile);
        String keyword = "";
        System.out.println(content);
        try {
            String [] keywords = WordUtil.getKeyWords(content);
            for(int i=0; i<keywords.length; i++){
                keyword += keywords[i] + " ";
            }
            System.out.println(keyword);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 插入到document表,插入到日志表,插入到关键字表
         */
        LoginRes loginRes = (LoginRes) request.getSession().getAttribute("loginUser");
        Documents documents = new Documents();
        documents.setReadNum(0);
        documents.setStatus(0);
        documents.setTitle(file.getOriginalFilename());//原文件名
        documents.setUrl(documentTag);
        documents.setDownloadNum(0);
        documents.setContent(outfile);//本地路径
        documents.setUsername(loginRes.getUsername());
        documents.setUserId(String.valueOf(loginRes.getUserId()));
        documentService.add(documents, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultRich.newInstance(ErrorCode.SYSTEM_ERROR);
        }
        return ResultRich.newInstance();
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/document/download/{documentId}", method = RequestMethod.GET)
    @ResponseBody
    public void downLoad(@PathVariable(value = "documentId") int documentId, HttpServletResponse response, HttpServletRequest request){
        ResultRich resultRich = new ResultRich();
        ServerResponseEnvelope<Documents>  serverResponseEnvelope = documentService.select(documentId);
        if(!serverResponseEnvelope.isSuccess() || serverResponseEnvelope.getSuccessResult() == null ){
            return;
        }
         File file = new File( serverResponseEnvelope.getSuccessResult().getContent());
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + serverResponseEnvelope.getSuccessResult().getId() + ".doc");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("----------file download" + serverResponseEnvelope.getSuccessResult().getTitle());
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /**
                 * 下载日志
                 */
                Logs logs = new Logs();
                logs.setDocumentId(documentId);
                LoginRes loginRes = (LoginRes) request.getSession().getAttribute("loginUser");
                logs.setOperater(loginRes.getUsername());
                logs.setStatus(4);
                iLogService.add(logs);
            }
        }
    }


}

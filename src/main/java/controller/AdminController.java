package controller;

import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.AdminReq;
import domain.response.LoginRes;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IAdminService;
import utils.MyProps;
import utils.SHA1Util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class AdminController {

    @Autowired
    IAdminService adminService;
    @Autowired
    MyProps myProps;

    /**
     * 修改密码
     */
    @RequestMapping(value = "/admin/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<LoginRes> update(@RequestBody AdminReq adminReq){
        ResultRich<LoginRes> resultRich = new ResultRich();
        File file = new File(myProps.getSecretKryPath());
        String secretKey = "";
        if(file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] b = new byte[1024];
                int len ;
                while((len = fileInputStream.read(b, 0, b.length)) != -1){
                    secretKey += new String(b);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        String password = "";
        String newPwd = "";
        try {
            byte[] bytes = SHA1Util.hexStrToByteArray(secretKey.trim());
            password = SHA1Util.encodeHmacSHA1(adminReq.getPassword().getBytes(),bytes);
            newPwd = SHA1Util.encodeHmacSHA1(adminReq.getNewPwd().getBytes(),bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminReq.setPassword(password);
        adminReq.setNewPwd(newPwd);
        ServerResponseEnvelope responseEnvelope = adminService.update(adminReq);
        if(!responseEnvelope.isSuccess()){
            if(responseEnvelope.getFailReasonStr().equals("1")){
                resultRich.setFail(ErrorCode.LOGIN_NAME_PASSWORD_WRONG);
            }else
                resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            LoginRes res = new LoginRes();
            Admin admin1 = (Admin) responseEnvelope.getSuccessResult();
            res.setUserId(admin1.getId());
            res.setUsername(admin1.getUsername());
            resultRich.setSuccess(res);
        }
        return resultRich;
    }

    @RequestMapping(value = "/admin/info",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich<LoginRes> detail( HttpServletRequest request){
        ResultRich<LoginRes> resultRich = new ResultRich();
        LoginRes loginRes = (LoginRes) request.getSession().getAttribute("loginUser");
       resultRich.setModel(loginRes);
        return  resultRich;
    }
}

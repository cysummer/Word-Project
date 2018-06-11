package controller;

import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.DocumentReq;
import domain.response.DocumentRes;
import domain.response.LoginRes;
import model.Admin;
import model.Documents;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IDocumentService;
import service.impl.DocumentServiceImpl;
import utils.PaginationQueryResult;

@RestController
public class DocumentController {

    private static final Logger logger            = LoggerFactory.getLogger(DocumentController.class);


    @Autowired
    IDocumentService documentService;


    /**
     * 获取文档列表
     */
    @RequestMapping(value = "/docuemnt/list",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<DocumentRes>> list(@RequestBody DocumentReq documentReq){
        ResultRich<PaginationQueryResult<DocumentRes>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<DocumentRes>> responseEnvelope =  documentService.list(documentReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }

    /**
     * 获取文档详情
     */
    @RequestMapping(value = "/document/detail/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich<DocumentRes> detail(@PathVariable(value = "id") int id){
        ResultRich<DocumentRes> resultRich = new ResultRich();
        ServerResponseEnvelope<DocumentRes> resServerResponseEnvelope = documentService.detail(id);
        if(!resServerResponseEnvelope.isSuccess())
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        else
            resultRich.setSuccess(resServerResponseEnvelope.getSuccessResult());
        return  resultRich;
    }

    /**
     * 在线浏览文档,添加日志
     */
    @RequestMapping(value = "/document/read",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<DocumentRes> read(@RequestBody DocumentReq documentReq, HttpServletRequest request){
        ResultRich<DocumentRes> resultRich = new ResultRich();
        LoginRes loginRes = (LoginRes) request.getSession().getAttribute("loginUser");
        Documents documents = new Documents();
        documents.setUrl(documentReq.getDocumentUrl());
        documents.setUsername(loginRes.getUsername());
        ServerResponseEnvelope<Documents> resServerResponseEnvelope = documentService.read(documents);
        return  resultRich;
    }

    /**
     * 文档审核接口
     */
    @RequestMapping(value = "/document/verify",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<DocumentRes> verify(@RequestBody DocumentReq documentReq, HttpServletRequest request){
        ResultRich<DocumentRes> resultRich = new ResultRich();
        LoginRes loginRes = (LoginRes) request.getSession().getAttribute("loginUser");
        ServerResponseEnvelope<DocumentRes> resServerResponseEnvelope = documentService.verify(documentReq.getDocumentId(), documentReq.getStatus(), loginRes.getUsername());
        if(!resServerResponseEnvelope.isSuccess())
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        else
            resultRich.setSuccess(resServerResponseEnvelope.getSuccessResult());
        return  resultRich;
    }

    /**
     * 获取某一用户的文档列表
     */
    @RequestMapping(value = "/document/user/list/",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<Documents>> documentUserlist(@RequestBody DocumentReq documentReq){
        ResultRich<PaginationQueryResult<Documents>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<Documents>> responseEnvelope =  documentService.documentUserlist(documentReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }



}

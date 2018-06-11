package controller;

import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.DocumentReq;
import domain.request.LogReq;
import domain.response.DocumentRes;
import domain.response.LogRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ILogService;
import utils.DateUtils;
import utils.PaginationQueryResult;

import java.util.Date;

/**
 * LogController
 * Create by cy on 2018/5/9
 **/

@RestController
public class LogController {


    @Autowired
    ILogService logService;

    /**
     * 获取日志列表
     */
    @RequestMapping(value = "/log/list",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<LogRes>> list(@RequestBody LogReq logReq){
        ResultRich<PaginationQueryResult<LogRes>> resultRich = new ResultRich();
        if(logReq.getPageNum() == null){
            return  ResultRich.newInstance(ErrorCode.MISSING_PARAMETER);
        }
        if(logReq.getPageSize() == null){
            return  ResultRich.newInstance(ErrorCode.MISSING_PARAMETER);
        }
        try {
            ServerResponseEnvelope<PaginationQueryResult<LogRes>> responseEnvelope =  logService.list(logReq);
            if(!responseEnvelope.isSuccess()){
                resultRich.setFail(ErrorCode.SYSTEM_ERROR);
            }else{
                resultRich.setSuccess(responseEnvelope.getSuccessResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }
        return  resultRich;
    }

    /**
     * 获取今日上传浏览次数
     */
    @RequestMapping(value = "/log/today",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich<LogRes> dayNum(){
        ResultRich<LogRes> resultRich = new ResultRich();
        try {
            Date date = DateUtils.getCurrDateTime();
            String time = DateUtils.getDateTimeStr(date, "yyyy-MM-dd");
            String start = time + " 00:00:00";
            String end = time + " 23:59:59";
            ServerResponseEnvelope<LogRes> responseEnvelope = logService.dayNum(DateUtils.parseDateTime(start),DateUtils.parseDateTime(end));
            if(!responseEnvelope.isSuccess()){
                resultRich.setFail(ErrorCode.SYSTEM_ERROR);
            }else{
                resultRich.setSuccess(responseEnvelope.getSuccessResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }
        return  resultRich;
    }





}

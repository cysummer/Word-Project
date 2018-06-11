package controller;

import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.LogReq;
import domain.request.StatisticReq;
import domain.response.LogRes;
import domain.response.StatisticRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ILogService;
import service.IStatisticService;
import utils.PaginationQueryResult;

/**
 * LogController
 * Create by cy on 2018/5/9
 * 日统计表
 **/

@RestController
public class StatisticController {


    @Autowired
    IStatisticService statisticService;


    /**
     * 获取日志列表
     */
    @RequestMapping(value = "/statistic/period",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<StatisticRes> dayNum(@RequestBody StatisticReq statisticReq){
        ResultRich<StatisticRes> resultRich = new ResultRich();
        if(statisticReq.getStart() == null){
            return  ResultRich.newInstance(ErrorCode.MISSING_PARAMETER);
        }
        if(statisticReq.getEnd() == null){
            return  ResultRich.newInstance(ErrorCode.MISSING_PARAMETER);
        }
        ServerResponseEnvelope<StatisticRes> responseEnvelope =  statisticService.count(statisticReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }


}

package controller;


import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.LabelReq;
import model.Labels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ILabelService;
import utils.PaginationQueryResult;

import java.util.List;

@RestController
public class LabelController {



    @Autowired
    private ILabelService labelService;

    @RequestMapping(value = "/label/list",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<Labels>> list(@RequestBody LabelReq labelReq){
        ResultRich<PaginationQueryResult<Labels>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<Labels>> responseEnvelope =  labelService.list(labelReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }

    /**
     * 获取一级标签列表
     */
    @RequestMapping(value = "/label/level/list",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich<List<Labels>> levelist(){
        ResultRich<List<Labels>> resultRich = new ResultRich();
        ServerResponseEnvelope<List<Labels>> responseEnvelope =  labelService.list();
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }


    /**
     * 添加标签
     */
    @RequestMapping(value = "/label/level/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<Labels>> addLevel(@RequestBody LabelReq labelReq){
        ResultRich<PaginationQueryResult<Labels>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<Labels>> responseEnvelope =  labelService.addLevel(labelReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.LABEL_NAME_REPETITION);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }

    /**
     * 修改标签
     */
    @RequestMapping(value = "/label/level/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<Labels>> update(@RequestBody LabelReq labelReq){
        ResultRich<PaginationQueryResult<Labels>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<Labels>> responseEnvelope =  labelService.update(labelReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.LABEL_NAME_REPETITION);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }

    @RequestMapping(value = "/label/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich delete(@PathVariable(value = "id") int id){
        ServerResponseEnvelope responseEnvelope =  labelService.delete(id);
        if(!responseEnvelope.isSuccess()){
            return ResultRich.newInstance(ErrorCode.SYSTEM_ERROR);
        }else{
            return ResultRich.newInstance(responseEnvelope.getSuccessResult());
        }
    }

}

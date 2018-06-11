package controller;

import com.alibaba.fastjson.JSONObject;
import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.UserReq;
import domain.response.LoginRes;
import model.Admin;
import model.Users;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IUserService;
import utils.DateUtils;
import utils.PaginationQueryResult;

import java.util.Date;

/**
 * Created by cy on 2017/6/27.
 */
@RestController
public class UserController {

    private static final Logger logger            = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    IUserService userService;

    /**
     *
     * @param Date 格式"yyyy-MM-dd"
     */
    @RequestMapping(value = "/user/list",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<PaginationQueryResult<Users>> list(@RequestBody UserReq userReq){
        ResultRich<PaginationQueryResult<Users>> resultRich = new ResultRich();
        ServerResponseEnvelope<PaginationQueryResult<Users>> responseEnvelope =  userService.list(userReq);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }

    @RequestMapping(value = "/user/detail/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich<Users> detail(@PathVariable(value = "id") int id){
        ResultRich<Users> resultRich = new ResultRich();
        ServerResponseEnvelope<Users> responseEnvelope = userService.selectById(id);
        if(!responseEnvelope.isSuccess()){
            resultRich.setFail(ErrorCode.SYSTEM_ERROR);
        }else{
            resultRich.setSuccess(responseEnvelope.getSuccessResult());
        }
        return  resultRich;
    }


}

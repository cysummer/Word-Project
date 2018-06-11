package service.impl;

import dao.UsersMapper;
import domain.ServerResponseEnvelope;
import domain.request.UserQueryParam;
import domain.request.UserReq;
import model.Users;
import model.UsersExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IUserService;
import utils.DateUtils;
import utils.PaginationQueryResult;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService{

    private static final Logger logger            = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    UsersMapper usersMapper;

    @Override
    public ServerResponseEnvelope<PaginationQueryResult<Users>> list(UserReq userReq) {
        Date start_time;
        Date end_time;
        String username;
        UserQueryParam userQueryParam = new UserQueryParam();
        ServerResponseEnvelope<PaginationQueryResult<Users>> result = new ServerResponseEnvelope<PaginationQueryResult<Users>>();
        userQueryParam.setPageNum(userReq.getPageNum());
        userQueryParam.setPageSize(userReq.getPageSize());

        if (userReq.getDate() != null) {
            String start = userReq.getDate() + " 00:00:00";
            String end = userReq.getDate() + " 23:59:59";
            start_time = DateUtils.parseDateTime(start);
            end_time = DateUtils.parseDateTime(end);
            userQueryParam.setStartTime(start_time);
            userQueryParam.setEndTime(end_time);
        }
        if (userReq.getUsername() != null) {
            username = userReq.getUsername();
            userQueryParam.setUsername(username);
        }
        try {
            int count =  usersMapper.countByPage(userQueryParam);
            if(count > 0){
                List<Users> list = usersMapper.selectByList(userQueryParam);
                if(list.size()>0){
                    PaginationQueryResult<Users> paginationQueryResult = new PaginationQueryResult<>();
                    paginationQueryResult.setResultList(list);
                    paginationQueryResult.setTotalSize(count);
                    return ServerResponseEnvelope.newInstance(paginationQueryResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
        return ServerResponseEnvelope.newInstance("error");
    }


    @Override
    public ServerResponseEnvelope<Users> selectById(int id) {
        ServerResponseEnvelope<Users> serverResponseEnvelope = new ServerResponseEnvelope<Users>();
        Users users =  usersMapper.selectByPrimaryKey(id);
        if(users != null){
            serverResponseEnvelope.setSuccessResult(users);
        }else
            serverResponseEnvelope.setFailReasonStr("error");
        return serverResponseEnvelope;
    }

  /*private ServerResponseEnvelope<Users> select(UserReq userReq){
      ServerResponseEnvelope<Users> serverResponseEnvelope = new ServerResponseEnvelope<Users>();
      Date start_time;
      Date end_time;
      String username;
      UserQueryParam userQueryParam = new UserQueryParam();

      String start = userReq.getDate() + " 00:00:00";
      String end = userReq.getDate() + " 23:59:59";
      start_time = DateUtils.parseDateTime(start);
      end_time = DateUtils.parseDateTime(end);
      userQueryParam.setStartTime(start_time);
      userQueryParam.setEndTime(end_time);
      UsersExample usersExample = new UsersExample();
      usersExample.createCriteria().andCreateTimeBetween(start_time,end_time).andUsernameLike(userReq.getUsername());
      return  serverResponseEnvelope;

  }*/


}

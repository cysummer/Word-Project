package service;

import domain.ServerResponseEnvelope;
import domain.request.UserReq;
import model.Users;
import org.apache.catalina.User;
import utils.PaginationQueryResult;

public interface IUserService {

    public ServerResponseEnvelope<PaginationQueryResult<Users>> list(UserReq userReq);

    public ServerResponseEnvelope<Users> selectById(int id);
}

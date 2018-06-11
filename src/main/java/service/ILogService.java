package service;

import domain.ServerResponseEnvelope;
import domain.request.LogReq;
import domain.response.LogRes;
import model.Logs;
import utils.PaginationQueryResult;

import java.util.Date;

/**
 * ILogService
 * Create by cy on 2018/5/9
 **/
public interface ILogService {

    public ServerResponseEnvelope<PaginationQueryResult<LogRes>> list(LogReq logReq);

    public ServerResponseEnvelope<LogRes> dayNum(Date start, Date end);

    public ServerResponseEnvelope<Logs> add(Logs logs);

}

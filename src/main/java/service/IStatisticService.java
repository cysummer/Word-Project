package service;

import domain.ServerResponseEnvelope;
import domain.request.StatisticReq;
import domain.response.StatisticRes;

/**
 * IStatisticService
 * Create by cy on 2018/5/10
 **/
public interface IStatisticService {

    public ServerResponseEnvelope<StatisticRes> count(StatisticReq statisticReq);

}

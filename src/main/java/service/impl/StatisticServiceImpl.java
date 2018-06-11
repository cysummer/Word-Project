package service.impl;

import dao.StatisticMapper;
import domain.ServerResponseEnvelope;
import domain.request.StatisticReq;
import domain.response.StatisticRes;
import model.Statistic;
import model.StatisticExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IStatisticService;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StatisticServiceImpl
 * Create by cy on 2018/5/10
 **/
@Service
public class StatisticServiceImpl implements IStatisticService{

    @Autowired
    StatisticMapper statisticMapper;

    @Override
    public ServerResponseEnvelope<StatisticRes> count(StatisticReq statisticReq) {
        Date start_time;
        Date end_time;
        StatisticRes statisticRes = new StatisticRes();
        ServerResponseEnvelope<StatisticRes> serverResponseEnvelope = new ServerResponseEnvelope<>();
        String start = statisticReq.getStart() + " 00:00:00";
        String end = statisticReq.getEnd() + " 23:59:59";
        start_time = DateUtils.parseDateTime(start);
        end_time = DateUtils.parseDateTime(end);
        try {
            StatisticExample statisticExample = new StatisticExample();
            statisticExample.createCriteria().andCreateTimeBetween(DateUtils.createByDays(start_time, 1),DateUtils.createByDays(end_time, 1));
            List<Statistic> list = statisticMapper.selectByExample(statisticExample);

            if(list.size() > 0){
                List<String> date = new ArrayList<>();
                List<Integer> readNum = new ArrayList<>();
                List<Integer> downNum = new ArrayList<>();
                List<Integer> uploadNum = new ArrayList<>();
                for(Statistic statistic : list){
                    Date date1 = DateUtils.createByDays(statistic.getCreateTime(), -1);
                    date.add(DateUtils.getDateTimeStr(date1,"yyyy-MM-dd"));
                    readNum.add(statistic.getReadNum());
                    downNum.add(statistic.getDownloadNum());
                    uploadNum.add(statistic.getUploadNum());
                }
                statisticRes.setDate(date);
                statisticRes.setDownNum(downNum);
                statisticRes.setReadNum(readNum);
                statisticRes.setUploadNum(uploadNum);
            }

            serverResponseEnvelope.setSuccessResult(statisticRes);
            return serverResponseEnvelope;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
    }
}

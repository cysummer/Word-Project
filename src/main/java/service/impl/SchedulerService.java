package service.impl;

import dao.LogsMapper;
import dao.StatisticMapper;
import domain.ServerResponseEnvelope;
import domain.response.LogRes;
import model.Logs;
import model.LogsExample;
import model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * SchedulerService
 * Create by cy on 2018/5/9
 **/

@Service
public class SchedulerService {

    @Autowired
    LogsMapper logsMapper;
    @Autowired
    StatisticMapper statisticMapper;

    @Scheduled(cron="0 0 1 * * ?") //每天上午1点执行
//    @Scheduled(cron = "0 0/1 15,17 * * ?")
    public void statusCheck() {
        Date date = DateUtils.createByDays(new Date(), -1);
        String time = DateUtils.getDateTimeStr(date, "yyyy-MM-dd");
        Date start = DateUtils.parseDateTime(time + " 00:00:00");
        Date end = DateUtils.parseDateTime(time + " 23:59:59");

        Statistic statistic = new Statistic();

        try {
            /**
             * 浏览
             */
            LogsExample logsExample = new LogsExample();
            logsExample.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(5);
            List<Logs> list = logsMapper.selectByExample(logsExample);
            statistic.setReadNum(list.size());
            /**
             * 下载
             */
            logsExample.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(4);
            list = logsMapper.selectByExample(logsExample);
            statistic.setDownloadNum(list.size());
            /**
             * 上传
             */
            logsExample.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(3);
            list = logsMapper.selectByExample(logsExample);
            statistic.setUploadNum(list.size());
            statisticMapper.insertSelective(statistic);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Date date = DateUtils.createByDays(new Date(), -1);
        String time = DateUtils.getDateTimeStr(date, "yyyy-MM-dd");
        String start = time + " 00:00:00";

        String end = time + " 23:59:59";
        DateUtils.parseDateTime(start);
        DateUtils.parseDateTime(end);
        System.out.println(DateUtils.parseDateTime(start) + "   | " + DateUtils.parseDateTime(end));
    }
}

package service.impl;

import dao.DocumentsMapper;
import dao.LogsMapper;
import domain.ServerResponseEnvelope;
import domain.request.LogQueryParam;
import domain.request.LogReq;
import domain.response.LogRes;
import model.Documents;
import model.DocumentsExample;
import model.Logs;
import model.LogsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ILogService;
import utils.DateUtils;
import utils.PaginationQueryResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LogService
 * Create by cy on 2018/5/9
 **/

@Service("logService")
public class LogServiceImpl implements ILogService{

    @Autowired
    LogsMapper logsMapper;
    @Autowired
    DocumentsMapper documentsMapper;

    @Override
    public ServerResponseEnvelope<PaginationQueryResult<LogRes>> list(LogReq logReq) {
        Date start_time;
        Date end_time;
        String username;
        LogQueryParam logQueryParam = new LogQueryParam();
        ServerResponseEnvelope<PaginationQueryResult<LogRes>> result = new ServerResponseEnvelope<PaginationQueryResult<LogRes>>();
        logQueryParam.setPageNum(logReq.getPageNum());
        logQueryParam.setPageSize(logReq.getPageSize());

        if (logReq.getDate() != null) {
            String start = logReq.getDate() + " 00:00:00";
            String end = logReq.getDate() + " 23:59:59";
            start_time = DateUtils.parseDateTime(start);
            end_time = DateUtils.parseDateTime(end);
            logQueryParam.setStartTime(start_time);
            logQueryParam.setEndTime(end_time);
        }
        if (logReq.getUsername() != null) {
            username = logReq.getUsername();
            logQueryParam.setUsername(username);
        }
        try {
            int count =  logsMapper.countByPage(logQueryParam);
            if(count > 0){
                List<Logs> logsList = logsMapper.selectByList(logQueryParam);
                PaginationQueryResult<LogRes> paginationQueryResult = new PaginationQueryResult<>();
                if(logsList.size()>0){
                    List<LogRes> logResList = new ArrayList<>();
                    /**
                     * 根据文档id寻找文档名
                     */
                    for(Logs logs : logsList){
                        LogRes logRes = new LogRes();
                        DocumentsExample documentsExample = new DocumentsExample();
                        documentsExample.createCriteria().andIdEqualTo(logs.getDocumentId());
                        List<Documents> documentsList = documentsMapper.selectByExample(documentsExample);
                        logRes.setCreateTime(logs.getCreateTime());
                        logRes.setDocumentName(documentsList.get(0).getTitle());
                        logRes.setStatus(logs.getStatus());
                        logRes.setUsername(logs.getOperater());
                        logResList.add(logRes);
                    }
                    paginationQueryResult.setResultList(logResList);
                    paginationQueryResult.setTotalSize(count);
                }
                result.setSuccessResult(paginationQueryResult);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
        return result;
    }

    @Override
    public ServerResponseEnvelope<LogRes> dayNum(Date start, Date end) {
        LogRes logRes = new LogRes();
        /**
         * 浏览
         */
        LogsExample logsExample = new LogsExample();
        try {
            logsExample.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(5);
            List<Logs> list = logsMapper.selectByExample(logsExample);
            logRes.setReadNum(list.size());
            /**
             * 下载
             */
            LogsExample logsExample2 = new LogsExample();
            logsExample2.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(4);
            List<Logs> list2 = logsMapper.selectByExample(logsExample2);
            logRes.setDownNum(list2.size());
            /**
             * 上传
             */
            LogsExample logsExample3 = new LogsExample();
            logsExample3.createCriteria().andCreateTimeBetween(start, end).andStatusEqualTo(3);
            List<Logs> list3 = logsMapper.selectByExample(logsExample3);
            logRes.setUploadNum(list3.size());
            return ServerResponseEnvelope.newInstance(logRes);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
    }

    @Override
    public ServerResponseEnvelope<Logs> add(Logs logs) {
        logsMapper.insertSelective(logs);
        return ServerResponseEnvelope.newInstance();
    }
}

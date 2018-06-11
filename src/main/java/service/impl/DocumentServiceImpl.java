package service.impl;

import com.alibaba.fastjson.JSON;
import dao.*;
import domain.ServerResponseEnvelope;
import domain.request.DocumentQueryParam;
import domain.request.DocumentReq;
import domain.response.DocumentRes;
import model.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IDocumentService;;
import utils.DateUtils;
import utils.PaginationQueryResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("documentService")
public class DocumentServiceImpl implements IDocumentService{

    private static final Logger logger   = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    DocumentsMapper documentsMapper;
    @Autowired
    KeywordsMapper keywordsMapper;
    @Autowired
    LabelsMapper labelsMapper;
    @Autowired
    LinkshipMapper linkshipMapper;
    @Autowired
    LogsMapper logsMapper;

    /**
     * 首先查找到document的list，然后根据这个list的id去link表中查找label的ID，然后找到label的name，加到label中,其次根据这个ID到keyword表查关键字
     * @return
     */
    @Override
    public ServerResponseEnvelope<PaginationQueryResult<DocumentRes>> list(DocumentReq documentReq) {
        Date start_time;
        Date end_time;
        DocumentQueryParam documentQueryParam = new DocumentQueryParam();
        ServerResponseEnvelope<PaginationQueryResult<DocumentRes>> result = new ServerResponseEnvelope<PaginationQueryResult<DocumentRes>>();
        documentQueryParam.setPageNum(documentReq.getPageNum());
        documentQueryParam.setPageSize(documentReq.getPageSize());
        if(documentReq.getTime() != null && !"".equals(documentReq.getTime())){
            Date end = DateUtils.createByHours(documentReq.getTime(),24);
            documentQueryParam.setStartTime(documentReq.getTime());
            documentQueryParam.setEndTime(end);
        }
        if (documentReq.getDate() != null && !"".equals(documentReq.getDate())) {
            String start = documentReq.getDate() + " 00:00:00";
            String end = documentReq.getDate() + " 23:59:59";
            start_time = DateUtils.parseDateTime(start);
            end_time = DateUtils.parseDateTime(end);
            documentQueryParam.setStartTime(start_time);
            documentQueryParam.setEndTime(end_time);
        }
        if (documentReq.getUsername() != null) {
            documentQueryParam.setUsername(documentReq.getUsername());
        }
        if(documentReq.getStatus() != null){
            documentQueryParam.setStatus(documentReq.getStatus());
        }
        int count =  documentsMapper.countByPage(documentQueryParam);
        if(count > 0){
            List<Documents> list = documentsMapper.selectByList(documentQueryParam);
            if(list.size()>0){
                PaginationQueryResult<DocumentRes> paginationQueryResult = new PaginationQueryResult<>();
                List<DocumentRes> documentResList = new ArrayList<>();
                for(Documents documents:list){

                    System.out.println(JSON.toJSONString(documents));

                    DocumentRes documentRes = new DocumentRes();
                    documentRes.setAuthor(documents.getAuthor());
                    documentRes.setContent(documents.getContent());
                    documentRes.setCreateTime(documents.getCreateTime());
                    documentRes.setDownloadNum(documents.getDownloadNum());
                    documentRes.setId(documents.getId());
                    documentRes.setReadNum(documents.getReadNum());
                    documentRes.setTitle(documents.getTitle());
                    documentRes.setStatus(documents.getStatus());
                    documentRes.setUsername(documents.getUsername());
                    documentRes.setUserId(documents.getUserId());
                    if(StringUtils.isNotEmpty(documents.getUrl()))
                        documentRes.setUrl(documents.getUrl());

                    KeywordsExample keywordsExample = new KeywordsExample();
                    keywordsExample.createCriteria().andDocIdEqualTo(documents.getId());
                    List<Keywords> keywords = keywordsMapper.selectByExample(keywordsExample);
                    if(keywords.size() > 0){
                        System.out.println(JSON.toJSONString(keywords.get(0)));
                        documentRes.setKeywords(keywords.get(0).getContentKey());
                    }

                    LinkshipExample linkshipExample = new LinkshipExample();
                    linkshipExample.createCriteria().andDocIdEqualTo(documents.getId());
                    List<Linkship> linkships =  linkshipMapper.selectByExample(linkshipExample);
                    String labels = "";
                    String labelId = "";
                    for(Linkship linkship:linkships){
                        System.out.println(JSON.toJSONString(linkship));
                        Labels label = labelsMapper.selectByPrimaryKey(linkship.getLabelId());
                        labels += label.getLableName() + " ";
                        labelId += label.getId() + "|";
                    }
                    documentRes.setLabelId(labelId);
                    documentRes.setLabelName(labels);
                    documentResList.add(documentRes);

                    System.out.println(JSON.toJSONString(documentRes));
                }
                paginationQueryResult.setTotalSize(count);
                paginationQueryResult.setResultList(documentResList);
                result.setSuccessResult(paginationQueryResult);
            }else
                result.setFailReasonStr("error");
        }
        return result;
    }

    @Override
    public ServerResponseEnvelope<PaginationQueryResult<Documents>> documentUserlist(DocumentReq  documentReq) {
        ServerResponseEnvelope<PaginationQueryResult<Documents>> result = new ServerResponseEnvelope<PaginationQueryResult<Documents>>();
        PaginationQueryResult<Documents> paginationQueryResult = new PaginationQueryResult<>();
        DocumentQueryParam documentQueryParam = new DocumentQueryParam();
        documentQueryParam.setUserId(documentReq.getUserId());
        int count =  documentsMapper.countByPage(documentQueryParam);
        if(count > 0){
            List<Documents> list = documentsMapper.selectByList(documentQueryParam);
            paginationQueryResult.setTotalSize(count);
            paginationQueryResult.setResultList(list);
            result.setSuccessResult(paginationQueryResult);
        }else
            result.setFailReasonStr("error");
        return result;
    }

    @Override
    public ServerResponseEnvelope<DocumentRes> detail(int id) {
        ServerResponseEnvelope<DocumentRes> serverResponseEnvelope = new ServerResponseEnvelope<>();
        DocumentRes documentRes = new DocumentRes();
        Documents documents = documentsMapper.selectByPrimaryKey(id);
        if(documents != null){
            documentRes.setAuthor(documents.getAuthor());
            documentRes.setContent(documents.getContent());
            documentRes.setCreateTime(documents.getCreateTime());
            documentRes.setDownloadNum(documents.getDownloadNum());
            documentRes.setId(documents.getId());
            documentRes.setReadNum(documents.getReadNum());
            documentRes.setTitle(documents.getTitle());
            documentRes.setStatus(documents.getStatus());
            documentRes.setUsername(documents.getUsername());
            documentRes.setUserId(documents.getUserId());
            if(StringUtils.isNotEmpty(documents.getUrl()))
                documentRes.setUrl(documents.getUrl());

            KeywordsExample keywordsExample = new KeywordsExample();
            keywordsExample.createCriteria().andDocIdEqualTo(documents.getId());
            List<Keywords> keywords = keywordsMapper.selectByExample(keywordsExample);
            if(keywords.size() > 0){
                System.out.println(JSON.toJSONString(keywords.get(0)));
                documentRes.setKeywords(keywords.get(0).getContentKey());
            }

            LinkshipExample linkshipExample = new LinkshipExample();
            linkshipExample.createCriteria().andDocIdEqualTo(documents.getId());
            List<Linkship> linkships =  linkshipMapper.selectByExample(linkshipExample);
            String labels = "";
            String labelId = "";
            for(Linkship linkship:linkships){
                System.out.println(JSON.toJSONString(linkship));
                Labels label = labelsMapper.selectByPrimaryKey(linkship.getLabelId());
                labels += label.getLableName() + " ";
                labelId += label.getId() + "|";
            }
            documentRes.setLabelId(labelId);
            documentRes.setLabelName(labels);
            serverResponseEnvelope.setSuccessResult(documentRes);
        }else
            serverResponseEnvelope.setFailReasonStr("error");
        return serverResponseEnvelope;
    }

    @Override
    public ServerResponseEnvelope<DocumentRes> verify(int documentId,  int status, String  username) {
        ServerResponseEnvelope<DocumentRes> serverResponseEnvelope = new ServerResponseEnvelope<>();
        try {
            Documents documents = new Documents();
            documents.setId(documentId);
            documents.setStatus(status);
            documentsMapper.updateByPrimaryKeySelective(documents);
            Logs logs = new Logs();
            logs.setDocumentId(documentId);
            logs.setOperater(username);
            logs.setStatus(status);
            logsMapper.insertSelective(logs);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponseEnvelope.setFailReasonStr("error");
        }
        return serverResponseEnvelope;
    }

    /**
     * 插入到日志表,插入到关键字表
     */
    @Override
    public ServerResponseEnvelope<Documents> add(Documents documents, String keyword) {
        ServerResponseEnvelope<Documents> serverResponseEnvelope = new ServerResponseEnvelope<>();
        try {
            documentsMapper.insertSelective(documents);
            /**
             * 选择最新一条记录
             */
            DocumentsExample documentsExample = new DocumentsExample();
            documentsExample.createCriteria().andUrlEqualTo(documents.getUrl());
            List<Documents> list = documentsMapper.selectByExample(documentsExample);
            if(list.size() > 0){
                Logs logs = new Logs();
                logs.setDocumentId(list.get(0).getId());
                logs.setOperater(documents.getUsername());
                logs.setStatus(3);
                logsMapper.insertSelective(logs);
                Keywords keywords = new Keywords();
                keywords.setDocId(list.get(0).getId());
                keywords.setContentKey(keyword);
                keywordsMapper.insertSelective(keywords);
            }
            /**
             * 文档分类
             */
            Linkship linkship = new Linkship();
            linkship.setDocId(list.get(0).getId());
            linkship.setLabelId(70);
            linkshipMapper.insertSelective(linkship);
            linkship.setDocId(list.get(0).getId());
            linkship.setLabelId(79);
            linkshipMapper.insertSelective(linkship);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponseEnvelope.setFailReasonStr("error");
        }
        return serverResponseEnvelope;
    }

    @Override
    public ServerResponseEnvelope<Documents> select(Integer documentId) {
        ServerResponseEnvelope<Documents> serverResponseEnvelope = new ServerResponseEnvelope<>();
        Documents documents = documentsMapper.selectByPrimaryKey(documentId);
        serverResponseEnvelope.setSuccessResult(documents);
        return serverResponseEnvelope;
    }

    /**
     * 浏览日志
     */
    @Override
    public ServerResponseEnvelope<Documents> read(Documents documents) {
        DocumentsExample documentsExample = new DocumentsExample();
        documentsExample.createCriteria().andUrlEqualTo(documents.getUrl());
        List<Documents> list = documentsMapper.selectByExample(documentsExample);
        if(list.size() > 0){
            Logs logs = new Logs();
            logs.setDocumentId(list.get(0).getId());
            logs.setOperater(documents.getUsername());
            logs.setStatus(5);
            logsMapper.insertSelective(logs);
        }
        return ServerResponseEnvelope.newInstance();
    }
}

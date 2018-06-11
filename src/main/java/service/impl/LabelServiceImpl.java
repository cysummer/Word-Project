package service.impl;

import dao.LabelsMapper;
import domain.ServerResponseEnvelope;
import domain.request.LabelQueryParam;
import domain.request.LabelReq;
import model.Labels;
import model.LabelsExample;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ILabelService;
import utils.PaginationQueryResult;

import java.util.Date;
import java.util.List;

@Service("labelService")
public class LabelServiceImpl implements ILabelService{


    @Autowired
    LabelsMapper labelsMapper;

    @Override
    public ServerResponseEnvelope<PaginationQueryResult<Labels>> list(LabelReq labelReq) {
        LabelQueryParam labelQueryParam = new LabelQueryParam();
        labelQueryParam.setPageNum(labelReq.getPageNum());
        labelQueryParam.setPageSize(labelReq.getPageSize());
        try {
            int count =  labelsMapper.countByPage(labelQueryParam);
            if(count >0 ){
                List<Labels> list = labelsMapper.selectByList(labelQueryParam);
                if(list.size() > 0){
                    PaginationQueryResult<Labels> paginationQueryResult = new PaginationQueryResult<>();
                    paginationQueryResult.setResultList(list);
                    paginationQueryResult.setTotalSize(count);
                    return ServerResponseEnvelope.newInstance(paginationQueryResult);
                }
            }
        } catch (Exception e) {
            return ServerResponseEnvelope.newInstance("error");
        }
        return ServerResponseEnvelope.newInstance("error");
    }

    /**
     * 获取一级标签列表
     * @return
     */
    @Override
    public ServerResponseEnvelope<List<Labels>> list() {
        LabelsExample labelsExample = new LabelsExample();
        labelsExample.createCriteria().andLevelEqualTo(1);
        try {
            List<Labels> list = labelsMapper.selectByExample(labelsExample);
            if(list.size() > 0){
                return ServerResponseEnvelope.newInstance(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
        return ServerResponseEnvelope.newInstance("error");
    }

    @Override
    public ServerResponseEnvelope<Users> selectById(int id) {
        return null;
    }

    @Override
    public ServerResponseEnvelope addLevel(LabelReq labelReq) {
        Labels labels = new Labels();
        labels.setLableName(labelReq.getLabelName());
        labels.setLevel(labelReq.getLevel());
        labels.setParentId(labelReq.getParentId());
        try {
            labelsMapper.insertSelective(labels);
            return ServerResponseEnvelope.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
    }

    @Override
    public ServerResponseEnvelope delete(int id) {
        try {
            labelsMapper.deleteByPrimaryKey(id);
            return ServerResponseEnvelope.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
    }

    @Override
    public ServerResponseEnvelope update(LabelReq labelReq) {
        Labels labels = new Labels();
        labels.setLableName(labelReq.getLabelName());
        labels.setLevel(labelReq.getLevel());
        labels.setParentId(labelReq.getParentId());
        labels.setId(labelReq.getLabelId());
        try {
            labelsMapper.updateByPrimaryKeySelective(labels);
            return ServerResponseEnvelope.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseEnvelope.newInstance("error");
        }
    }


}

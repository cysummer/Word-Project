package dao;

import java.util.List;

import domain.request.LabelQueryParam;
import model.Labels;
import model.LabelsExample;

public interface LabelsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Labels record);

    int insertSelective(Labels record);

    List<Labels> selectByExample(LabelsExample example);

    Labels selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Labels record);

    int updateByPrimaryKey(Labels record);

    List<Labels> selectByList(LabelQueryParam labelQueryParam);

    int countByPage(LabelQueryParam labelQueryParam);
}
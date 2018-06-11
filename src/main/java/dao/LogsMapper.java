package dao;

import java.util.List;

import domain.request.LogQueryParam;
import model.Logs;
import model.LogsExample;

public interface LogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logs record);

    int insertSelective(Logs record);

    List<Logs> selectByExample(LogsExample example);

    Logs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);

    List<Logs> selectByList(LogQueryParam logQueryParam);

    int countByPage(LogQueryParam logQueryParam);
}
package dao;

import java.util.List;
import model.Statistic;
import model.StatisticExample;

public interface StatisticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    List<Statistic> selectByExample(StatisticExample example);

    Statistic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);
}
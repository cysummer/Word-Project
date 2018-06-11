package dao;

import java.util.List;
import model.Keywords;
import model.KeywordsExample;

public interface KeywordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Keywords record);

    int insertSelective(Keywords record);

    List<Keywords> selectByExampleWithBLOBs(KeywordsExample example);

    List<Keywords> selectByExample(KeywordsExample example);

    Keywords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Keywords record);

    int updateByPrimaryKeyWithBLOBs(Keywords record);

    int updateByPrimaryKey(Keywords record);
}
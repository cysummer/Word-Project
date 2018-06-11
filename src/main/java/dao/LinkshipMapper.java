package dao;

import java.util.List;
import model.Linkship;
import model.LinkshipExample;

public interface LinkshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Linkship record);

    int insertSelective(Linkship record);

    List<Linkship> selectByExample(LinkshipExample example);

    Linkship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Linkship record);

    int updateByPrimaryKey(Linkship record);
}
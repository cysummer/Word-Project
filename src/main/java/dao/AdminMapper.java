package dao;

import java.util.List;
import model.Admin;
import model.AdminExample;
import org.apache.ibatis.annotations.Mapper;

public interface AdminMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
package dao;

import java.util.List;

import domain.request.UserQueryParam;
import model.Users;
import model.UsersExample;

public interface UsersMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<Users> selectByList(UserQueryParam userQueryParam);

    int countByPage(UserQueryParam userQueryParam);

}
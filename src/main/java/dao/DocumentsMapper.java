package dao;

import java.util.List;

import domain.request.DocumentQueryParam;
import model.Documents;
import model.DocumentsExample;

public interface DocumentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Documents record);

    int insertSelective(Documents record);

    List<Documents> selectByExampleWithBLOBs(DocumentsExample example);

    List<Documents> selectByExample(DocumentsExample example);

    Documents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Documents record);

    int updateByPrimaryKeyWithBLOBs(Documents record);

    int updateByPrimaryKey(Documents record);

    List<Documents> selectByList(DocumentQueryParam documentQueryParam);

    int countByPage(DocumentQueryParam documentQueryParam);
}
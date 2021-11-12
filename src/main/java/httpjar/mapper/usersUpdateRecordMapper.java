package httpjar.mapper;

import httpjar.model.po.usersUpdateRecord;
import java.util.List;

public interface usersUpdateRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(usersUpdateRecord record);

    usersUpdateRecord selectByPrimaryKey(Integer id);

    List<usersUpdateRecord> selectAll();

    int updateByPrimaryKey(usersUpdateRecord record);
}
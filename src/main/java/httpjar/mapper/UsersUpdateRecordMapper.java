package httpjar.mapper;

import httpjar.model.po.UsersUpdateRecord;
import java.util.List;

public interface UsersUpdateRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsersUpdateRecord record);

    UsersUpdateRecord selectByPrimaryKey(Integer id);

    List<UsersUpdateRecord> selectAll();

    List<UsersUpdateRecord> selectRecordByUserId(Integer user_id);

    int updateByPrimaryKey(UsersUpdateRecord record);
}
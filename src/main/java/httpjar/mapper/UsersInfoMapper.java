package httpjar.mapper;

import httpjar.model.po.UsersInfo;
import java.util.List;

public interface UsersInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsersInfo record);

    UsersInfo selectByPrimaryKey(Integer id);

    List<UsersInfo> selectAll();

    int updateByPrimaryKey(UsersInfo record);


}
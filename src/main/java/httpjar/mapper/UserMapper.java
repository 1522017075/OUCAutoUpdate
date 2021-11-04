package httpjar.mapper;

import httpjar.model.po.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByAccount(String Account);

    List<User> selectAll();

    List<User> selectAutoUpdateUsers();

    int updateByPrimaryKey(User record);
}
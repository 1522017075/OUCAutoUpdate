package httpjar.service.Impl;

import httpjar.mapper.UserMapper;
import httpjar.model.po.User;
import httpjar.service.AutoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AutoUpdateServiceImpl implements AutoUpdateService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAutoUpdateUsers() {
        return userMapper.selectAutoUpdateUsers();
    }
}

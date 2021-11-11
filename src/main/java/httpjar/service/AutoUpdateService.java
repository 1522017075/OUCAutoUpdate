package httpjar.service;

import httpjar.model.po.User;

import java.io.IOException;
import java.util.List;

public interface AutoUpdateService {
    List<User> getAutoUpdateUsers();

    void execAutoUpdateByUserId(Integer userId);

    void cancelAutoUpdateByUserId(Integer userId);

    void startAutoUpdateByUserId(Integer userId);
}

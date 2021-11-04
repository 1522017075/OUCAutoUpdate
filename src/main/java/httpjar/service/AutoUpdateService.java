package httpjar.service;

import httpjar.model.po.User;

import java.util.List;

public interface AutoUpdateService {
    List<User> getAutoUpdateUsers();
}

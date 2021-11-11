package httpjar.service;

import httpjar.model.po.User;

public interface UserService {
    User checkUser(String account);

    User registerUser(String account);
}

package httpjar.service;

import httpjar.model.po.User;

public interface UserService {
    boolean checkUser(String account);

    User registerUser(String account);
}

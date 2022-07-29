package com.sabetski.edi.service;

import com.sabetski.edi.entity.User;

public interface UserService {
    User saveUser (String name, String login, String email);
    User getUserById(Integer id);
    User updateUser(Integer id, String name, String login, String email);
    void deleteUserById(Integer id);
}

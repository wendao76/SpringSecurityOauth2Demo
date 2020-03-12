package com.github.wendao76.repository;


import com.github.wendao76.entity.User;

public interface UserService {
    User create(String username, String password);
}

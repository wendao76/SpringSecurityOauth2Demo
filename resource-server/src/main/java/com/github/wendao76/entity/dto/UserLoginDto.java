package com.github.wendao76.entity.dto;

import com.github.wendao76.entity.JWT;
import com.github.wendao76.entity.User;

public class UserLoginDto {
    private JWT jwt;
    private User user;

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "jwt=" + jwt +
                ", user=" + user +
                '}';
    }
}

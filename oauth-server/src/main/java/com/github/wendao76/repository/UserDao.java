package com.github.wendao76.repository;


import com.github.wendao76.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO 功能待完善
 *
 * @Description  类描述
 * @Author lwh
 * @Version 1.0
 * @Date 2020/3/11 14:40
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

}

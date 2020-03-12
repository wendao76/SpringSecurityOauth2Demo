package com.github.wendao76.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Description 类描述信息
 * @ClassName AuthorizationData
 * @Author tiger
 * @Date 2020/3/12 15:46
 * @Version 1.0
 */

@Component
public class AuthorizationData implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
//
//        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//        BaseClientDetails clientDetails = null;
//        for (int i=0; i<5; i++) {
//            clientDetails = new BaseClientDetails("client_" + i, Utils.RESOURCEIDS.ORDER, "client",
//                    "client_credentials,refresh_token,authorization_code", "oauth2");
//            clientDetails.setClientSecret(finalSecret);
//
//            clientDetailsService.addClientDetails(clientDetails);
//        }
//        for (int i=5; i<10; i++) {
//            clientDetails = new BaseClientDetails("client_" + i, Utils.RESOURCEIDS.ORDER, "server",
//                    "password,refresh_token,authorization_code", "oauth2");
//            clientDetails.setClientSecret(finalSecret);
//            clientDetailsService.addClientDetails(clientDetails);
//        }
    }
}

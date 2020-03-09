package com.github.wendao76;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * oauth服务端
 * @author wendao76
 */
@SpringBootApplication
@EnableEurekaClient
public class OAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(OAuthServer.class, args);
    }
}

package com.github.wendao76;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/**
 * 资源服务客户端
 * @author wendao76
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class ResourceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}

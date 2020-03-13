package com.github.wendao76.controller;

import com.github.wendao76.entity.User;
import com.github.wendao76.entity.dto.UserLoginParamDto;
import com.github.wendao76.repository.UserDao;
import com.github.wendao76.util.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Collections;

@RequestMapping("/user")
@RestController
@Configuration
public class UserController {
    @Autowired
    UserDao userRepository;

    @Autowired
    OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        return new ResourceOwnerPasswordResourceDetails();
    }

    @Autowired
    RestTemplate restTemplate;

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @RequestMapping("/login")
    public ResponseEntity<OAuth2AccessToken> login(@Valid UserLoginParamDto loginDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors())
            throw new Exception("登录信息错误，请确认后再试");

        User user = userRepository.findByUsername(loginDto.getUsername());

        if (null == user)
            throw new Exception("用户为空，出错了");

        if (!BPwdEncoderUtil.matches(loginDto.getPassword(), user.getPassword().replace("{bcrypt}","")))
            throw new Exception("密码不正确");

        String client_secret = oAuth2ClientProperties.getClientId()+":"+oAuth2ClientProperties.getClientSecret();

        client_secret = "Basic "+Base64.getEncoder().encodeToString(client_secret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",client_secret);

        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(loginDto.getUsername()));
        map.put("password", Collections.singletonList(loginDto.getPassword()));
        map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));

        map.put("scope", oAuth2ProtectedResourceDetails.getScope());
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity(map,httpHeaders);
        //获取 Token
        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST,httpEntity, OAuth2AccessToken.class);

    }
}

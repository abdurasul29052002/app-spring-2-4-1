package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.payload.LoginDto;
import com.example.appspringhomework241.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        return loginService.loginToSystem(loginDto, httpServletResponse);
    }

}

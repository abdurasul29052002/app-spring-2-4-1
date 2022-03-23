package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.payload.LoginDto;
import com.example.appspringhomework241.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class MyUserController {

    @Autowired
    MyUserService myUserService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody LoginDto loginDto){
        return myUserService.registerUser(loginDto);
    }

    @GetMapping
    public HttpEntity<?> getAllUsers(){
        return myUserService.getAllUsers();
    }

    @GetMapping("/{username}")
    public HttpEntity<?> getUserByUsername(@PathVariable String username){
        return myUserService.getUserByUsername(username);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@PathVariable Integer id, @RequestBody LoginDto loginDto) {
        return myUserService.updateUser(id,loginDto);
    }
}

package com.example.appspringhomework241.controller;

import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class HomepageController {
    @GetMapping
    public HttpEntity<?> homepage(){
        return ResponseEntity.ok("Welcome to homepage");
    }
}

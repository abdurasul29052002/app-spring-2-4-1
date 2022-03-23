package com.example.appspringhomework241.service;

import com.example.appspringhomework241.component.JwtProvider;
import com.example.appspringhomework241.payload.LoginDto;
import com.example.appspringhomework241.payload.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public HttpEntity<?> loginToSystem(LoginDto loginDto, HttpServletResponse httpServletResponse) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = jwtProvider.generateToken(loginDto.getUsername());
            httpServletResponse.setStatus(200);
            httpServletResponse.setHeader("Authorization","Bearer "+token);
            return ResponseEntity.ok(new Result("Successfully",true));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(401).body(new Result("Username or login error",false));
        }
    }
}

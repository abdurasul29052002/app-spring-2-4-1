package com.example.appspringhomework241.service;

import com.example.appspringhomework241.entity.MyUser;
import com.example.appspringhomework241.payload.LoginDto;
import com.example.appspringhomework241.payload.Result;
import com.example.appspringhomework241.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService {
    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public HttpEntity<?> registerUser(LoginDto loginDto){
        boolean exists = myUserRepository.existsByUsername(loginDto.getUsername());
        if (exists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("This username is already registered",false));
        }
        MyUser myUser = new MyUser(null,loginDto.getUsername(),passwordEncoder.encode(loginDto.getPassword()));
        myUserRepository.save(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Result("User saved. Please noe LogIn",true));
    }

    public HttpEntity<?> getAllUsers() {
        return ResponseEntity.ok(myUserRepository.findAll());
    }

    public HttpEntity<?> updateUser(Integer id, LoginDto loginDto) {
        Optional<MyUser> optionalMyUser = myUserRepository.findById(id);
        if (!optionalMyUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("User not found",false));
        MyUser myUser = optionalMyUser.get();
        myUser.setUsername(loginDto.getUsername());
        myUser.setPassword(loginDto.getPassword());
        myUserRepository.save(myUser);
        return ResponseEntity.ok(new Result("User updated",true));
    }

    public HttpEntity<?> deleteUser(Integer id){
        try {
            myUserRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Result("User deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Error",false));
        }
    }

    public HttpEntity<?> getUserByUsername(String username) {
        Optional<MyUser> optionalMyUser = myUserRepository.findByUsername(username);
        return ResponseEntity.status(optionalMyUser.isPresent()?200:409).body(optionalMyUser.orElse(null));
    }
}

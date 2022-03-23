package com.example.appspringhomework241.service;

import com.example.appspringhomework241.entity.MyUser;
import com.example.appspringhomework241.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> optionalUser = myUserRepository.findByUsername(username);
        if (!optionalUser.isPresent())
            return null;
        MyUser myUser = optionalUser.get();
        User user = new User(myUser.getUsername(),myUser.getPassword(),new ArrayList<>());
        return user;
    }
}

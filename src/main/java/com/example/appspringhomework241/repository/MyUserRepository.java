package com.example.appspringhomework241.repository;

import com.example.appspringhomework241.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findByUsername(String username);

    boolean existsByUsername(String username);
}

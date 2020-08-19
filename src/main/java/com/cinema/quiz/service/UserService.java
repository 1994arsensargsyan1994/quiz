package com.cinema.quiz.service;

import com.cinema.quiz.entity.user.UserEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity getUserEntity(Long id);

   Optional< UserEntity> findById(Long userId);


    

    Optional<UserEntity> get(String email, String password);

    boolean userExist(String email);

    void delete(Long userId);

    UserEntity findByUsername(String username);

    UserEntity addUser(UserEntity user);
}

package com.cinema.quiz.service.impl;

import com.cinema.quiz.entity.user.Role;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.repo.RoleRepo;
import com.cinema.quiz.repo.UserRepo;
import com.cinema.quiz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepo userRepository;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepo;
        this.roleRepo = roleRepo;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity getUserEntity(Long id) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity;
    }



    @Override
    public UserEntity addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(false);
        Role userRole = roleRepo.findByName("ROLE_USER");
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }


    @Override
    public Optional<UserEntity> get(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean userExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        log.info("In findByUsername - {} user found by username: {} ", user, username);
        return user;
    }


}

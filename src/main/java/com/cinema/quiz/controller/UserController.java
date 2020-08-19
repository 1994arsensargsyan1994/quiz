package com.cinema.quiz.controller;


import com.cinema.quiz.dto.UserDto;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService usersService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService service) {
        this.usersService = service;

    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() throws ExecutionException {


        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userId") Long userId) throws Exception {

        Optional<UserEntity> user = usersService.findById(userId);

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.from(user.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        this.usersService.delete(userId);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId) {
        this.usersService.delete(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserEntity userEntity) {
        UserEntity userFromDb = this.usersService.addUser(userEntity);
        return ResponseEntity.ok(UserDto.from(userFromDb));
    }

}

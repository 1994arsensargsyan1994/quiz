package com.cinema.quiz.controller;


import com.cinema.quiz.dto.UserDto;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private  final UserService userService;



    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {

       Optional< UserEntity> user = userService.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.from(user.get());
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

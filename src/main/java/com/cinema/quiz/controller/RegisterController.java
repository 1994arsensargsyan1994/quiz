package com.cinema.quiz.controller;


import com.cinema.quiz.dto.UserDto;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user, BindingResult bindingResult) {

        if(userService.findByUsername(user.getUsername()) != null){
            bindingResult
                    .rejectValue("username", "error.userEntity",
                            "There is already a user registered with the email provided");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserEntity userForDb = userService.addUser(UserEntity.from(user));

        return ResponseEntity.ok(UserDto.from(userForDb));
    }


}

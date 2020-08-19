package com.cinema.quiz.controller;

import com.cinema.quiz.dto.UserDto;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.security.jwt.JwtTokenProvider;
import com.cinema.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/auth/login")
public class AuthenticationRestControllerV1 {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

//    {
//        "firstNameOrLastName":"YANG"
//    }


    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDto dto) {

        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            UserEntity user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User whit  username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("invalid username or password");
        }
    }


}

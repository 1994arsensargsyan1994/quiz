package com.cinema.quiz.controller;


import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.exceptions.UserNotFoundException;
import com.cinema.quiz.service.FootballPlayerService;
import com.cinema.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/league/")
public class FootballPlayersController {


    private final FootballPlayerService footballPlayerService;
    private final UserService userService;


    @Autowired
    public FootballPlayersController(FootballPlayerService service, UserService userService) {
        this.footballPlayerService = service;

        this.userService = userService;
    }

    @GetMapping("anglia")
    public ResponseEntity<List<FootballPlayers>> footballPlayersFranc() throws UserNotFoundException {
        List<FootballPlayers> all = footballPlayerService.findAll(2);
        return ResponseEntity.ok(all);
    }

    @GetMapping("ital")
    public ResponseEntity<List<FootballPlayers>> footballPlayersItal() throws UserNotFoundException {


        List<FootballPlayers> all = footballPlayerService.findAll(1);
        return ResponseEntity.ok(all);
    }
}


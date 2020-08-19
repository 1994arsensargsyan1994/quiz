package com.cinema.quiz.controller;


import com.cinema.quiz.dto.FormName;
import com.cinema.quiz.entity.Player;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.service.*;
import com.cinema.quiz.service.impl.ServiceAnswerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final PlayerService playerService;
    private final ServiceAnswer serviceAnswer;
    private final UserService userService;


    @Autowired
    public GameController(PlayerService gameService, FootballPlayerService footballPlayerService, UnavailableFootballPlayerService unavailableFootballPlayerService, ServiceAnswerImpl service, UserService userService) {
        this.playerService = gameService;
        this.serviceAnswer = service;
        this.userService = userService;
    }


    @PostMapping("/player1")
    public Boolean getAnswer1(@RequestBody FormName formName) {
        Player player1 = playerService.findByName("Player1");
        Player player2 = playerService.findByName("Player2");
        String[] firstNameOrLastName = formName.getFirstNameOrLastName().split(" ");

        Boolean answerBoolean = serviceAnswer.getBoolean(player1,player2, firstNameOrLastName);
        initFlag(player1, answerBoolean);
        return answerBoolean;


    }

    @PostMapping("/player2")
    public Boolean getAnswer2(@RequestBody FormName formName) {
        Player player2 = playerService.findByName("Player2");
        Player player1 = playerService.findByName("Player1");
        String[] firstNameOrLastName = formName.getFirstNameOrLastName().toUpperCase().split(" ");

        Boolean answerBoolean = serviceAnswer.getBoolean(player2, player1,firstNameOrLastName);
        initFlag(player2, answerBoolean);
        return answerBoolean;


    }

    private void initFlag(Player player2, Boolean answerBoolean) {
        if (!answerBoolean) {
            Long user_id = player2.getUser_id();
            UserEntity userFromDb = userService.findById(user_id).get();
            userFromDb.setIsActive(false);
            userService.addUser(userFromDb);
        }
    }



}
    
    


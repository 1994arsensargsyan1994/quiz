package com.cinema.quiz.service.impl;

import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.entity.Player;
import com.cinema.quiz.entity.UnavailableFootballPlayer;
import com.cinema.quiz.repo.PlayerRepo;
import com.cinema.quiz.repo.guava.FootballGuava;
import com.cinema.quiz.service.FootballPlayerService;
import com.cinema.quiz.service.PlayerService;
import com.cinema.quiz.service.ServiceAnswer;
import com.cinema.quiz.service.UnavailableFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAnswerImpl implements ServiceAnswer {


    private final UnavailableFootballPlayerService unavailableFootballPlayerService;
    private final PlayerRepo playerRepo;


    @Autowired
    public ServiceAnswerImpl(UnavailableFootballPlayerService unavailableFootballPlayerService, PlayerRepo playerRepo) {


        this.unavailableFootballPlayerService = unavailableFootballPlayerService;
        this.playerRepo = playerRepo;
    }

    @Override
    public Boolean getBoolean(Player player1,Player player2, String[] firstNameOrLastName) {
        FootballPlayers footballPlayers;
        if (firstNameOrLastName.length == 1) {
            UnavailableFootballPlayer unavailableFootballPlayer = unavailableFootballPlayerService.getFootballPlayer(firstNameOrLastName[0]);
            footballPlayers = FootballPlayers.from(unavailableFootballPlayer);
            if (check(footballPlayers, player1)) {
                return player1.getIsTrue();
            }
            footballPlayers = FootballGuava.getFootballPlayersByFirstName(firstNameOrLastName[0]);
            if (footballPlayers == null) {
                return false;
            }
            player1.setAnswer(firstNameOrLastName[0]);
            if (!checkAnswer(player1, player2)) {
                return false;
            }

            unavailableFootballPlayerService.save(footballPlayers);
            player1.setIsTrue(true);
            return player1.getIsTrue();

        } else if (firstNameOrLastName.length == 2) {
            UnavailableFootballPlayer footballPlayer = unavailableFootballPlayerService.
                    getFootballPlayer(firstNameOrLastName[0].toLowerCase(), firstNameOrLastName[1]);
            footballPlayers = FootballPlayers.from(footballPlayer);
            if (check(footballPlayers, player1)) {
                return player1.getIsTrue();
            }
            footballPlayers = FootballGuava.getFootballPlayersByFirstName(firstNameOrLastName[0], firstNameOrLastName[1]);
            if (footballPlayers == null) {
                return false;
            }
            player1.setAnswer(firstNameOrLastName[1]);
            if (!checkAnswer(player1, player2)) {
                return false;
            }
            unavailableFootballPlayerService.save(footballPlayers);
            player1.setIsTrue(true);
            return player1.getIsTrue();
        }
        throw new IllegalArgumentException();
    }

    private boolean checkAnswer(Player player1, Player player2) {
        if (player1.getCount() != null) {
            String player1Answer = player1.getAnswer();
            String player2Answer = player2.getAnswer();
            if (player2Answer != null) {

                StringBuilder input1 = new StringBuilder(player2Answer);
                player2Answer = input1.reverse().toString();
                Character a = player1Answer.charAt(0);
                Character b = player2Answer.charAt(0);
                if (!a.equals(b)){
                    return false;
                }

            }
        }else {
            player2.setCount(1);
            player1.setCount(1);
        }

        return true;
    }

    private boolean check(FootballPlayers footballPlayers, Player player1) {
        if (footballPlayers != null) {

            player1.setIsTrue(false);

            return true;
        }
        return false;
    }

}

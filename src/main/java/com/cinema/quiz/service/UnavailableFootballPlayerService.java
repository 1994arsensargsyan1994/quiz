package com.cinema.quiz.service;

import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.entity.UnavailableFootballPlayer;

import java.util.List;

public interface UnavailableFootballPlayerService {



    void save(FootballPlayers footballPlayers);

    void  delete();

    UnavailableFootballPlayer getFootballPlayer(String firstName, String lastName);
    UnavailableFootballPlayer getFootballPlayer(String firstName);
}

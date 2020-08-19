package com.cinema.quiz.service;

import com.cinema.quiz.entity.Player;

import java.util.List;
import java.util.Set;

public interface PlayerService {

    Set<Player> startNewGame();

    List<Player> getAllPlayers();

    Player findByName(String name);

    void delete();


}

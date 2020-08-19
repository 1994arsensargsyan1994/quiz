package com.cinema.quiz.service;

import com.cinema.quiz.entity.Player;

public interface ServiceAnswer {
    Boolean getBoolean(Player player1,Player player, String[] firstNameOrLastName);
}

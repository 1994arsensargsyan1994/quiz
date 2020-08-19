package com.cinema.quiz.service.impl;

import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.repo.FootballPlayersRepo;
import com.cinema.quiz.repo.guava.FootballGuava;

import com.cinema.quiz.service.FootballPlayerService;
import com.cinema.quiz.service.PlayerService;
import com.cinema.quiz.service.UnavailableFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {


    private final FootballPlayersRepo footballPlayersRepo;
    private final PlayerService playerService;
    private  final UnavailableFootballPlayerService unavailableFootballPlayerService;
    private  final FootballGuava guava;

    @Autowired
    public FootballPlayerServiceImpl(FootballPlayersRepo repo, PlayerService playerService, UnavailableFootballPlayerService unavailableFootballPlayerService, FootballGuava guava) {
        this.footballPlayersRepo = repo;
        this.playerService = playerService;
        this.unavailableFootballPlayerService = unavailableFootballPlayerService;
        this.guava = guava;
    }


    @Override
    public List<FootballPlayers> findAll(Integer id) {
        List<FootballPlayers> allByLeagueId = footballPlayersRepo.findAllByLeagueId(id);
        this.playerService.startNewGame();
         this.unavailableFootballPlayerService.delete();
        this.guava.addFootballPlayers(allByLeagueId);

        return allByLeagueId;
    }


}

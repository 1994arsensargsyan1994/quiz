package com.cinema.quiz.service.impl;

import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.entity.UnavailableFootballPlayer;
import com.cinema.quiz.repo.UnavailableFootballPlayerRepo;
import com.cinema.quiz.repo.UnavailableFootballPlayerRepoTruncate;
import com.cinema.quiz.service.UnavailableFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UnavailableFootballPlayerServiceImpl implements UnavailableFootballPlayerService {


    private final UnavailableFootballPlayerRepo unavailableFootballPlayerRepo;
    private  final UnavailableFootballPlayerRepoTruncate truncateTable; // examples


    @Autowired
    public UnavailableFootballPlayerServiceImpl(UnavailableFootballPlayerRepo unavailableFootballPlayerRepo, UnavailableFootballPlayerRepoTruncate truncateTable) {
        this.unavailableFootballPlayerRepo = unavailableFootballPlayerRepo;

        this.truncateTable = truncateTable;
    }


    @Override
    public void save(FootballPlayers footballPlayers) {
        unavailableFootballPlayerRepo.save(UnavailableFootballPlayer.from(footballPlayers));
    }

    @Override
    public void delete() {
   truncateTable.delete();
    }

    @Override
    public UnavailableFootballPlayer getFootballPlayer(String firstName,String lastName) {
        return unavailableFootballPlayerRepo.findByFirstNameOrLastName(firstName,lastName);
    }

    @Override
    public UnavailableFootballPlayer getFootballPlayer(String firstName) {

        UnavailableFootballPlayer foot = unavailableFootballPlayerRepo.findByFirstName(firstName);
        if (foot == null){
            foot = unavailableFootballPlayerRepo.findByLastName(firstName);
        }
        return foot;

    }



}

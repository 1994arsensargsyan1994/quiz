package com.cinema.quiz.repo;

import com.cinema.quiz.entity.FootballPlayers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface FootballPlayersRepo extends JpaRepository<FootballPlayers,Long> {



    List<FootballPlayers> findAllByLeagueId(Integer id);

    FootballPlayers findByFirstNameOrLastName(String firstName, String lastName);
    FootballPlayers findByFirstName(String firstName);
    FootballPlayers findByLastName(String lastName);
}

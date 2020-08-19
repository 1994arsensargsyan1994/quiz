package com.cinema.quiz.repo;


import com.cinema.quiz.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepo  extends JpaRepository<Player,Integer> {

    Player findByName(String name);





}

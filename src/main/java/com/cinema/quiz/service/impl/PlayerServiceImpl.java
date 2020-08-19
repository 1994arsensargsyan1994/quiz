package com.cinema.quiz.service.impl;

import com.cinema.quiz.entity.Player;
import com.cinema.quiz.entity.user.UserEntity;
import com.cinema.quiz.repo.PlayerRepo;
import com.cinema.quiz.repo.UserRepo;
import com.cinema.quiz.security.jwt.JwtUser;
import com.cinema.quiz.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final UserRepo userRepo;


    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo, UserRepo userRepo) {
        this.playerRepo = playerRepo;
        this.userRepo = userRepo;
    }


    @Override
    public Set<Player> startNewGame() {
        delete();

        Set<Player> players = getPlayers();


        playerRepo.saveAll(players);


        return players;
    }

    private Set<Player> getPlayers() {
        Set<Player> players = new HashSet<>(2);
        Player player1 = new Player("Player1", false);
        Player player2 = new Player("Player2", false);

        setId(player1);


        Iterable<UserEntity> userEntitiesByIsActive = userRepo.findAllByIsActive();
        if (userEntitiesByIsActive.iterator().hasNext()) {
            UserEntity next = userEntitiesByIsActive.iterator().next();
            next.setIsActive(true);
            player2.setUser_id(next.getId());
        } else {
            player2.setName("Computer");
        }





        players.add(player1);
        players.add(player2);
        return players;
    }

    private void setId(Player player1) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Optional<UserEntity> fromDb = userRepo.findById(jwtUser.getId());
        if (fromDb.isPresent()) {
            UserEntity userEntity = fromDb.get();
            userEntity.setIsActive(true);
            player1.setUser_id(userEntity.getId());
        }

    }


    @Override
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @Override
    public Player findByName(String name) {
        return playerRepo.findByName(name);
    }

    @Override
    public void delete() {

        playerRepo.deleteAll();

    }


}
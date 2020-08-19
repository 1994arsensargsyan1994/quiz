package com.cinema.quiz.repo;

import com.cinema.quiz.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    @Query("SELECT c FROM UserEntity c WHERE c.isActive = false ")
    Iterable<UserEntity> findAllByIsActive();


    @Query
    Iterable<UserEntity> findByIsActiveFalse();


}

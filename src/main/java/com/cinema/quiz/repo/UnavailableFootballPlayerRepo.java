package com.cinema.quiz.repo;
import com.cinema.quiz.entity.UnavailableFootballPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UnavailableFootballPlayerRepo  extends JpaRepository<UnavailableFootballPlayer,Long> {

    UnavailableFootballPlayer findByFirstNameOrLastName(String firstName, String lastName);
    UnavailableFootballPlayer findByFirstName(String firstName);
    UnavailableFootballPlayer findByLastName(String lastName);

}

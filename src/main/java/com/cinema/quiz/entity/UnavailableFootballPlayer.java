package com.cinema.quiz.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@Builder
public class UnavailableFootballPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public static UnavailableFootballPlayer from(FootballPlayers footballPlayers) {

      return UnavailableFootballPlayer.builder()
              .firstName(footballPlayers.getFirstName())
              .lastName(footballPlayers.getLastName())
              .build();
    }

}

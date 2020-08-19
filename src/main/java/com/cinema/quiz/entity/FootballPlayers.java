package com.cinema.quiz.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@Builder
public class FootballPlayers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonManagedReference
    private League league;

    public static FootballPlayers from(UnavailableFootballPlayer unavailablefootballPlayer) {
        if (unavailablefootballPlayer == null){
            return null;
        }

        return FootballPlayers.builder()
                .firstName(unavailablefootballPlayer.getFirstName())
                .lastName(unavailablefootballPlayer.getLastName())
                .build();
    }
}

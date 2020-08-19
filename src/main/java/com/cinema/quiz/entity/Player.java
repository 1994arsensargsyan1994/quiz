package com.cinema.quiz.entity;


import com.cinema.quiz.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String name;
    Boolean isTrue;

    public Player(String name, Boolean isTrue) {
        this.name = name;
        this.isTrue = isTrue;
    }

  //  @OneToOne
    @JoinColumn(name = "user_id")
    private Long  user_id;

    private String answer;

    private Integer count;
}

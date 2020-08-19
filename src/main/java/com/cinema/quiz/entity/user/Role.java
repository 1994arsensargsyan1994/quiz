package com.cinema.quiz.entity.user;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {


    @Id
    Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)


    private List<UserEntity> users;

//    @Override
//    public String toString() {
//        return super.toString() + "Role{" +
//                "name='" + name + '\'' +
//                ", users=" + users +
//                '}';
//    }
}

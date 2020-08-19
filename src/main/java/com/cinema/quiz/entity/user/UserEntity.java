package com.cinema.quiz.entity.user;


import com.cinema.quiz.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String username;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}

    )
    @ToString.Exclude
    List<Role> roles;

    @Column(name = "is_active")
    private Boolean isActive;


    public static UserEntity from(UserDto userDto) {
        return UserEntity.builder().password(userDto.getPassword())
                .username(userDto.getUsername()).build();
    }
}

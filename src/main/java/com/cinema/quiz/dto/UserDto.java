package com.cinema.quiz.dto;


import com.cinema.quiz.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {


    private String username;

//    @JsonIgnore
    private String password;


    public static UserDto from(UserEntity userEntity) {
        return UserDto.builder().
                password(userEntity.getPassword()).
                username(userEntity.getUsername()).build();
    }
}

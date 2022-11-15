package com.example.watchigo.dto;

import com.example.watchigo.entity.UserEntity;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private Long A_SEQ;
    private String A_ID;
    private String A_PW;
    private String A_NAME;
    private String A_PHONE;

    @Builder
    public UserDto(Long a_SEQ, String a_ID, String a_PW, String a_NAME, String a_PHONE) {
        A_SEQ = a_SEQ;
        A_ID = a_ID;
        A_PW = a_PW;
        A_NAME = a_NAME;
        A_PHONE = a_PHONE;
    }

    public UserEntity toEntity(){
        UserEntity entity = UserEntity.builder()
                .a_seq(A_SEQ)
                .a_id(A_ID)
                .a_pw(A_PW)
                .a_name(A_NAME)
                .a_phone(A_PHONE)
                .build();
        return entity;
    }
}

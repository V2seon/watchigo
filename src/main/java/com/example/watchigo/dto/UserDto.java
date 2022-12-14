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
    private String A_DATE;
    private String A_PHONE;
    private String A_EMAIL;

    @Builder
    public UserDto(Long a_SEQ, String a_ID, String a_PW, String a_DATE, String a_NAME, String a_PHONE, String a_EMAIL) {
        A_SEQ = a_SEQ;
        A_ID = a_ID;
        A_PW = a_PW;
        A_NAME = a_NAME;
        A_DATE = a_DATE;
        A_PHONE = a_PHONE;
        A_EMAIL = a_EMAIL;
    }

    public UserEntity toEntity(){
        UserEntity entity = UserEntity.builder()
                .a_seq(A_SEQ)
                .a_id(A_ID)
                .a_pw(A_PW)
                .a_date(A_DATE)
                .a_name(A_NAME)
                .a_phone(A_PHONE)
                .a_email(A_EMAIL)
                .build();
        return entity;
    }
}

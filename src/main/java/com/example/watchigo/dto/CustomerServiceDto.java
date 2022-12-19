package com.example.watchigo.dto;

import com.example.watchigo.entity.CustomerServiceEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerServiceDto {

    private Long A_PK;
    private Long A_SEQ;
    private String A_TYPE;
    private String A_TITLE;
    private String A_TEXT;
    private String A_NAME;
    private String A_DATE;
    private String A_STATE;

    @Builder
    public CustomerServiceDto(Long a_PK,Long a_SEQ, String a_TYPE, String a_TITLE,
                              String a_DATE, String a_NAME, String a_TEXT, String a_STATE) {
        A_PK = a_PK;
        A_SEQ = a_SEQ;
        A_TYPE = a_TYPE;
        A_TITLE = a_TITLE;
        A_TEXT = a_TEXT;
        A_NAME = a_NAME;
        A_DATE = a_DATE;
        A_STATE = a_STATE;
    }

    public CustomerServiceEntity toEntity(){
        CustomerServiceEntity entity = CustomerServiceEntity.builder()
                .a_pk(A_PK)
                .a_seq(A_SEQ)
                .a_type(A_TYPE)
                .a_title(A_TITLE)
                .a_text(A_TEXT)
                .a_name(A_NAME)
                .a_date(A_DATE)
                .a_state(A_STATE)
                .build();
        return entity;
    }

}

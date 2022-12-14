package com.example.watchigo.dto;

import com.example.watchigo.entity.RentangleEntiry;
import com.example.watchigo.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RectangleDto {

    private Long A_PK;
    private Long A_USERSEQ;
    private String A_DATE;
    private String A_SPOINT;
    private String A_EPOINT;

    @Builder
    public RectangleDto(Long a_PK, Long a_USERSEQ, String a_DATE, String a_SPOINT, String a_EPOINT) {
        A_PK = a_PK;
        A_USERSEQ = a_USERSEQ;
        A_DATE = a_DATE;
        A_SPOINT = a_SPOINT;
        A_EPOINT = a_EPOINT;
    }

    public RentangleEntiry toEntity(){
        RentangleEntiry entity = RentangleEntiry.builder()
                .a_pk(A_PK)
                .a_userseq(A_USERSEQ)
                .a_date(A_DATE)
                .a_spoint(A_SPOINT)
                .a_epoint(A_EPOINT)
                .build();
        return entity;
    }

}

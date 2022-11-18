package com.example.watchigo.dto;

import com.example.watchigo.entity.CircleEntity;
import com.example.watchigo.entity.PolygonEntity;
import lombok.*;

import java.awt.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PolygonDto {

    private Long A_SEQ;
    private Long A_PK;
    private Long A_USERSEQ;
    private String A_DATE;
    private Long A_POINTSEQ;
    private String A_POINT1;


    @Builder
    public PolygonDto(Long a_SEQ, Long a_PK, Long a_USERSEQ, String a_DATE, Long a_POINTSEQ, String a_POINT1) {
        A_SEQ = a_SEQ;
        A_PK = a_PK;
        A_USERSEQ = a_USERSEQ;
        A_DATE = a_DATE;
        A_POINTSEQ = a_POINTSEQ;
        A_POINT1 = a_POINT1;
    }

    public PolygonEntity toEntity(){
        PolygonEntity entity = PolygonEntity.builder()
                .a_seq(A_SEQ)
                .a_pk(A_PK)
                .a_userseq(A_USERSEQ)
                .a_date(A_DATE)
                .a_pointseq(A_POINTSEQ)
                .a_point1(A_POINT1)
                .build();
        return entity;
    }
}

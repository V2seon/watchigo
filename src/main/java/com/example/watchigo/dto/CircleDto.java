package com.example.watchigo.dto;

import com.example.watchigo.entity.CircleEntity;
import com.example.watchigo.entity.RentangleEntiry;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CircleDto {

    private Long A_PK;
    private Long A_USERSEQ;
    private String A_DATE;
    private String A_CENTER;
    private String A_RADIUS;

    @Builder
    public CircleDto(Long a_PK, Long a_USERSEQ, String a_DATE, String a_CENTER, String a_RADIUS) {
        A_PK = a_PK;
        A_USERSEQ = a_USERSEQ;
        A_DATE = a_DATE;
        A_CENTER = a_CENTER;
        A_RADIUS = a_RADIUS;
    }

    public CircleEntity toEntity(){
        CircleEntity entity = CircleEntity.builder()
                .a_pk(A_PK)
                .a_userseq(A_USERSEQ)
                .a_date(A_DATE)
                .a_center(A_CENTER)
                .a_radius(A_RADIUS)
                .build();
        return entity;
    }

}

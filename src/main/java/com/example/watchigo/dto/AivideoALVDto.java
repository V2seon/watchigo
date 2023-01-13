package com.example.watchigo.dto;

import com.example.watchigo.entity.AivideoALVEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AivideoALVDto {

    private Long ALV_SEQ;
    private Long ALV_U_SEQ;
    private String ALV_CLASS;
    private String ALV_NAME;
    private String ALV_VIDEO;
    private int ALV_STATE;
    private String ALV_DATETIME;

    @Builder
    public AivideoALVDto(Long alv_SEQ, Long alv_U_SEQ, String alv_CLASS, String alv_NAME,
                         String alv_VIDEO, int alv_STATE, String alv_DATETIME) {
        ALV_SEQ = alv_SEQ;
        ALV_U_SEQ = alv_U_SEQ;
        ALV_CLASS = alv_CLASS;
        ALV_NAME = alv_NAME;
        ALV_VIDEO = alv_VIDEO;
        ALV_STATE = alv_STATE;
        ALV_DATETIME = alv_DATETIME;
    }

    public AivideoALVEntity toEntity(){
        AivideoALVEntity entity = AivideoALVEntity.builder()
                .alv_seq(ALV_SEQ)
                .alv_u_seq(ALV_U_SEQ)
                .alv_class(ALV_CLASS)
                .alv_name(ALV_NAME)
                .alv_video(ALV_VIDEO)
                .alv_state(ALV_STATE)
                .alv_datetime(ALV_DATETIME)
                .build();
        return entity;
    }
}

package com.example.watchigo.dto;

import com.example.watchigo.entity.AivideoALDEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AivideoALDDto {

    private Long ALD_PK;
    private Long ALD_ALV_SEQ;
    private String ALD_IMGNAME;
    private int ALD_IMGCNT;
    private String ALD_MAINCNT;
    private int ALD_WIDTH;
    private int ALD_HEIGHT;
    private String ALD_MAINBOX;
    private String ALD_LABELNAME;
    private String ALD_DATETIME;

    @Builder
    public AivideoALDDto(Long ald_PK, Long ald_ALV_SEQ, String ald_IMGNAME, int ald_IMGCNT,
                         String ald_MAINCNT, int ald_WIDTH, int ald_HEIGHT, String ald_MAINBOX,
                         String ald_LABELNAME, String ald_DATETIME) {
        ALD_PK = ald_PK;
        ALD_ALV_SEQ = ald_ALV_SEQ;
        ALD_IMGNAME = ald_IMGNAME;
        ALD_IMGCNT = ald_IMGCNT;
        ALD_MAINCNT = ald_MAINCNT;
        ALD_WIDTH = ald_WIDTH;
        ALD_HEIGHT = ald_HEIGHT;
        ALD_MAINBOX = ald_MAINBOX;
        ALD_LABELNAME = ald_LABELNAME;
        ALD_DATETIME = ald_DATETIME;
    }

    public AivideoALDEntity toEntity(){
        AivideoALDEntity entity = AivideoALDEntity.builder()
                .ald_pk(ALD_PK)
                .ald_alv_seq(ALD_ALV_SEQ)
                .ald_imgname(ALD_IMGNAME)
                .ald_imgcnt(ALD_IMGCNT)
                .ald_maincnt(ALD_MAINCNT)
                .ald_width(ALD_WIDTH)
                .ald_height(ALD_HEIGHT)
                .ald_mainbox(ALD_MAINBOX)
                .ald_labelname(ALD_LABELNAME)
                .ald_datetime(ALD_DATETIME)
                .build();
        return entity;
    }
}

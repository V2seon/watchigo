package com.example.watchigo.entity;

import com.example.watchigo.dto.AivideoALDDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "AI_LABELING_DATA")
public class AivideoALDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALD_PK")
    private Long aldpk;

    @Column(name = "ALD_ALV_SEQ")
    private Long aldalvuseq;

    @Column(name = "ALD_IMGNAME")
    private String aldimgname;

    @Column(name = "ALD_IMGCNT")
    private int aldimgcnt;

    @Column(name = "ALD_MAINCNT")
    private String aldmaincnt;

    @Column(name = "ALD_WIDTH")
    private int aldwidth;

    @Column(name = "ALD_HEIGHT")
    private int aldheight;

    @Column(name = "ALD_MAINBOX")
    private String aldmainbox;

    @Column(name = "ALD_LABELNAME")
    private String aldlabelname;

    @Column(name = "ALD_DATETIME")
    private String alddatetime;

    @Builder
    public AivideoALDEntity(Long ald_pk, Long ald_alv_seq, String ald_imgname, int ald_imgcnt,
                            String ald_maincnt, int ald_width, int ald_height, String ald_mainbox,
                            String ald_labelname, String ald_datetime) {
        this.aldpk = ald_pk;
        this.aldalvuseq = ald_alv_seq;
        this.aldimgname = ald_imgname;
        this.aldimgcnt = ald_imgcnt;
        this.aldmaincnt = ald_maincnt;
        this.aldwidth = ald_width;
        this.aldheight = ald_height;
        this.aldmainbox = ald_mainbox;
        this.aldlabelname = ald_labelname;
        this.alddatetime = ald_datetime;
    }

    public static AivideoALDEntity toSaveEntity (AivideoALDDto AivideoALDDto){
        AivideoALDEntity aivideoALDEntity = new AivideoALDEntity();
        aivideoALDEntity.setAldpk(AivideoALDDto.getALD_PK());
        aivideoALDEntity.setAldalvuseq(AivideoALDDto.getALD_ALV_SEQ());
        aivideoALDEntity.setAldimgname(AivideoALDDto.getALD_IMGNAME());
        aivideoALDEntity.setAldimgcnt(AivideoALDDto.getALD_IMGCNT());
        aivideoALDEntity.setAldmaincnt(AivideoALDDto.getALD_MAINCNT());
        aivideoALDEntity.setAldwidth(AivideoALDDto.getALD_WIDTH());
        aivideoALDEntity.setAldheight(AivideoALDDto.getALD_HEIGHT());
        aivideoALDEntity.setAldmainbox(AivideoALDDto.getALD_MAINBOX());
        aivideoALDEntity.setAldlabelname(AivideoALDDto.getALD_LABELNAME());
        aivideoALDEntity.setAlddatetime(AivideoALDDto.getALD_DATETIME());

        return aivideoALDEntity;
    }
}

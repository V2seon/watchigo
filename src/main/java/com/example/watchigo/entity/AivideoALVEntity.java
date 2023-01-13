package com.example.watchigo.entity;

import com.example.watchigo.dto.AivideoALVDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "AI_LABELING_VIDEO")
public class AivideoALVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALV_SEQ")
    private Long alvseq;

    @Column(name = "ALV_U_SEQ")
    private Long alvuseq;

    @Column(name = "ALV_CLASS")
    private String alvclass;

    @Column(name = "ALV_NAME")
    private String alvname;

    @Column(name = "ALV_VIDEO")
    private String alvvideo;

    @Column(name = "ALV_STATE")
    private int alvstate;

    @Column(name = "ALV_DATETIME")
    private String alvdatetime;

    @Builder
    public AivideoALVEntity(Long alv_seq, Long alv_u_seq, String alv_class, String alv_name,
                            String alv_video, int alv_state, String alv_datetime) {
        this.alvseq = alv_seq;
        this.alvuseq = alv_u_seq;
        this.alvclass = alv_class;
        this.alvname = alv_name;
        this.alvvideo = alv_video;
        this.alvstate = alv_state;
        this.alvdatetime = alv_datetime;
    }

    public static AivideoALVEntity toSaveEntity (AivideoALVDto AivideoALVDto){
        AivideoALVEntity aivideoALVEntity = new AivideoALVEntity();
        aivideoALVEntity.setAlvseq(AivideoALVDto.getALV_SEQ());
        aivideoALVEntity.setAlvuseq(AivideoALVDto.getALV_U_SEQ());
        aivideoALVEntity.setAlvclass(AivideoALVDto.getALV_CLASS());
        aivideoALVEntity.setAlvname(AivideoALVDto.getALV_NAME());
        aivideoALVEntity.setAlvvideo(AivideoALVDto.getALV_VIDEO());
        aivideoALVEntity.setAlvstate(AivideoALVDto.getALV_STATE());
        aivideoALVEntity.setAlvdatetime(AivideoALVDto.getALV_DATETIME());

        return aivideoALVEntity;
    }
}

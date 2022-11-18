package com.example.watchigo.entity;

import com.example.watchigo.dto.CircleDto;
import com.example.watchigo.dto.PolygonDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "polygon")
public class PolygonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long aseq;

    @Column(name = "pk")
    private Long apk;

    @Column(name = "userseq")
    private Long auserseq;

    @Column(name = "date")
    private String adate;

    @Column(name = "pointseq")
    private Long apointseq;

    @Column(name = "point1")
    private String apoint1;

    @Builder
    public PolygonEntity(Long a_seq, Long a_pk, Long a_userseq, String a_date, Long a_pointseq, String a_point1) {
        this.aseq = a_seq;
        this.apk = a_pk;
        this.auserseq = a_userseq;
        this.adate = a_date;
        this.apointseq = a_pointseq;
        this.apoint1 = a_point1;
    }

    public static PolygonEntity toSaveEntity (PolygonDto PolygonDto){
        PolygonEntity polygonEntity = new PolygonEntity();
        polygonEntity.setApk(PolygonDto.getA_PK());
        polygonEntity.setAuserseq(PolygonDto.getA_USERSEQ());
        polygonEntity.setAdate(PolygonDto.getA_DATE());
        polygonEntity.setApointseq(PolygonDto.getA_POINTSEQ());
        polygonEntity.setApoint1(PolygonDto.getA_POINT1());
        return polygonEntity;
    }
}

package com.example.watchigo.entity;

import com.example.watchigo.dto.CircleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "circle")
public class CircleEntity {

    @Id
    @Column(name = "pk")
    private Long apk;

    @Column(name = "userseq")
    private Long auserseq;

    @Column(name = "date")
    private String adate;

    @Column(name = "center")
    private String acenter;

    @Column(name = "radius")
    private String aradius;

    @Builder
    public CircleEntity(Long a_pk, Long a_userseq, String a_date, String a_center, String a_radius) {
        this.apk = a_pk;
        this.auserseq = a_userseq;
        this.adate = a_date;
        this.acenter = a_center;
        this.aradius = a_radius;
    }

    public static CircleEntity toSaveEntity (CircleDto CircleDto){
        CircleEntity circleEntity = new CircleEntity();
        circleEntity.setApk(CircleDto.getA_PK());
        circleEntity.setAuserseq(CircleDto.getA_USERSEQ());
        circleEntity.setAdate(CircleDto.getA_DATE());
        circleEntity.setAcenter(CircleDto.getA_CENTER());
        circleEntity.setAradius(CircleDto.getA_RADIUS());
        return circleEntity;
    }
}

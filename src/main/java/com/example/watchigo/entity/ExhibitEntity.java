package com.example.watchigo.entity;

import com.example.watchigo.dto.ExhibitDto;
import com.example.watchigo.dto.ServiceZoneDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "exhibit")
public class ExhibitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", unique = true)
    private Long seq;

    @Column(name = "pk")
    private Long pk;

    @Column(name = "userseq")
    private Long userseq;

    @Column(name = "type")
    private String type;

    @Column(name = "typename")
    private String typename;

    @Column(name = "zonename")
    private String zonename;

    @Column(name = "name")
    private String name;

    @Column(name = "ex")
    private String ex;

    @Column(name = "point")
    private String point;

    @Column(name = "video1")
    private String video1;

    @Column(name = "video2")
    private String video2;

    @Column(name = "img1")
    private String img1;

    @Column(name = "img2")
    private String img2;

    @Column(name = "img3")
    private String img3;

    @Column(name = "img4")
    private String img4;

    @Column(name = "img5")
    private String img5;

    @Column(name = "img6")
    private String img6;

    @Column(name = "mainicon")
    private String mainicon;

    @Column(name = "armarker")
    private String armarker;

    @Column(name = "printtype")
    private int printtype;

    @Column(name = "date")
    private String date;

    @Builder
    public ExhibitEntity(Long pk, Long seq,Long userseq, String type, String typename, String point, String zonename, String name, String ex, String video1, String video2,
                         String img1, String img2, String img3, String img4, String img5, String img6,
                         String mainicon,String armarker, int printtype ,String date){
        this.seq = seq;
        this.pk = pk;
        this.userseq = userseq;
        this.type = type;
        this.typename = typename;
        this.zonename = zonename;
        this.name = name;
        this.ex =ex;
        this.point =point;
        this.video1 =video1;
        this.video2 =video2;
        this.img1 =img1;
        this.img2 =img2;
        this.img3 =img3;
        this.img4 =img4;
        this.img5 =img5;
        this.img6 =img6;
        this.mainicon = mainicon;
        this.armarker =armarker;
        this.printtype =printtype;
        this.date =date;
    }

    public static ExhibitEntity toSaveEntity (ExhibitDto ExhibitDto){
        ExhibitEntity exhibitEntity = new ExhibitEntity();
        exhibitEntity.setSeq(exhibitEntity.getSeq());
        exhibitEntity.setPk(exhibitEntity.getPk());
        exhibitEntity.setUserseq(exhibitEntity.getUserseq());
        exhibitEntity.setType(exhibitEntity.getType());
        exhibitEntity.setTypename(exhibitEntity.getTypename());
        exhibitEntity.setZonename(exhibitEntity.getZonename());
        exhibitEntity.setName(exhibitEntity.getName());
        exhibitEntity.setEx(exhibitEntity.getEx());
        exhibitEntity.setPoint(exhibitEntity.getPoint());
        exhibitEntity.setVideo1(exhibitEntity.getVideo1());
        exhibitEntity.setVideo2(exhibitEntity.getVideo2());
        exhibitEntity.setImg1(exhibitEntity.getImg1());
        exhibitEntity.setImg2(exhibitEntity.getImg2());
        exhibitEntity.setImg3(exhibitEntity.getImg3());
        exhibitEntity.setImg4(exhibitEntity.getImg4());
        exhibitEntity.setImg5(exhibitEntity.getImg5());
        exhibitEntity.setImg6(exhibitEntity.getImg6());
        exhibitEntity.setMainicon(exhibitEntity.getMainicon());
        exhibitEntity.setPrinttype(exhibitEntity.getPrinttype());
        exhibitEntity.setDate(exhibitEntity.getDate());

        return exhibitEntity;
    }
}



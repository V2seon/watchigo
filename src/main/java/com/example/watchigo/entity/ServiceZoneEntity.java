package com.example.watchigo.entity;


import com.example.watchigo.dto.ServiceZoneDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "servicezone")
public class ServiceZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", unique = true)
    private Long pk;

    @Column(name = "seq")
    private Long seq;

    @Column(name = "zonename")
    private String zonename;

    @Column(name = "state")
    private int state;

    @Column(name = "address")
    private String address;

    @Column(name = "address1")
    private String address1;

    @Column(name = "ex")
    private String ex;

    @Column(name = "type")
    private int type;

    @Column(name = "date")
    private String date;

    @Column(name = "marker")
    private String marker;

    @Column(name = "vedio1")
    private String vedio1;

    @Column(name = "vedio2")
    private String vedio2;

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


    @Builder
    public ServiceZoneEntity(Long pk, Long seq, String zonename, int state, String address, String address1,
                             String ex, int type, String date, String marker, String vedio1, String vedio2,
                             String img1, String img2, String img3, String img4, String img5, String img6) {
        this.pk = pk;
        this.seq = seq;
        this.zonename = zonename;
        this.address =address;
        this.address1 =address1;
        this.ex =ex;
        this.type =type;
        this.date =date;
        this.marker =marker;
        this.vedio1 =vedio1;
        this.vedio2 =vedio2;
        this.img1 =img1;
        this.img2 =img2;
        this.img3 =img3;
        this.img4 =img4;
        this.img5 =img5;
        this.img6 =img6;
    }

    public static ServiceZoneEntity toSaveEntity (ServiceZoneDto ServiceZoneDto){
        ServiceZoneEntity serviceZoneEntity = new ServiceZoneEntity();
        serviceZoneEntity.setPk(ServiceZoneDto.getA_pk());
        serviceZoneEntity.setSeq(ServiceZoneDto.getA_seq());
        serviceZoneEntity.setAddress(ServiceZoneDto.getA_address());
        serviceZoneEntity.setAddress1(ServiceZoneDto.getA_address1());
        serviceZoneEntity.setEx(ServiceZoneDto.getA_ex());
        serviceZoneEntity.setType(ServiceZoneDto.getA_type());
        serviceZoneEntity.setDate(ServiceZoneDto.getA_date());
        serviceZoneEntity.setMarker(ServiceZoneDto.getA_marker());
        serviceZoneEntity.setVedio1(ServiceZoneDto.getA_vedio1());
        serviceZoneEntity.setVedio2(ServiceZoneDto.getA_vedio2());
        serviceZoneEntity.setImg1(ServiceZoneDto.getA_img1());
        serviceZoneEntity.setImg2(ServiceZoneDto.getA_img2());
        serviceZoneEntity.setImg3(ServiceZoneDto.getA_img3());
        serviceZoneEntity.setImg4(ServiceZoneDto.getA_img4());
        serviceZoneEntity.setImg5(ServiceZoneDto.getA_img5());
        serviceZoneEntity.setImg6(ServiceZoneDto.getA_img6());

        return serviceZoneEntity;
    }
}

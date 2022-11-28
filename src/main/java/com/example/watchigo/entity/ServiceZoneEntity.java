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

    @Column(name = "zonecenter")
    private String zonecenter;

    @Column(name = "state")
    private String state;

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

    @Builder
    public ServiceZoneEntity(Long pk, Long seq, String zonename, String zonecenter, String state, String address, String address1,
                             String ex, int type, String date, String marker, String video1, String video2,
                             String img1, String img2, String img3, String img4, String img5, String img6) {
        this.pk = pk;
        this.seq = seq;
        this.zonename = zonename;
        this.zonecenter = zonecenter;
        this.state = state;
        this.address =address;
        this.address1 =address1;
        this.ex =ex;
        this.type =type;
        this.date =date;
        this.marker =marker;
        this.video1 =video1;
        this.video2 =video2;
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
        serviceZoneEntity.setZonename(ServiceZoneDto.getA_zonename());
        serviceZoneEntity.setZonecenter(ServiceZoneDto.getA_zonecenter());
        serviceZoneEntity.setState(ServiceZoneDto.getA_state());
        serviceZoneEntity.setAddress(ServiceZoneDto.getA_address());
        serviceZoneEntity.setAddress1(ServiceZoneDto.getA_address1());
        serviceZoneEntity.setEx(ServiceZoneDto.getA_ex());
        serviceZoneEntity.setType(ServiceZoneDto.getA_type());
        serviceZoneEntity.setDate(ServiceZoneDto.getA_date());
        serviceZoneEntity.setMarker(ServiceZoneDto.getA_marker());
        serviceZoneEntity.setVideo1(ServiceZoneDto.getA_video1());
        serviceZoneEntity.setVideo2(ServiceZoneDto.getA_video2());
        serviceZoneEntity.setImg1(ServiceZoneDto.getA_img1());
        serviceZoneEntity.setImg2(ServiceZoneDto.getA_img2());
        serviceZoneEntity.setImg3(ServiceZoneDto.getA_img3());
        serviceZoneEntity.setImg4(ServiceZoneDto.getA_img4());
        serviceZoneEntity.setImg5(ServiceZoneDto.getA_img5());
        serviceZoneEntity.setImg6(ServiceZoneDto.getA_img6());

        return serviceZoneEntity;
    }
}

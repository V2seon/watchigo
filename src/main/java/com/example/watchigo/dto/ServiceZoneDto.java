package com.example.watchigo.dto;

import com.example.watchigo.entity.ServiceZoneEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ServiceZoneDto {
    private Long a_pk;
    private Long a_seq;
    private String a_zonename;
    private String a_zonecenter;
    private String a_state;
    private String a_address;
    private String a_address1;
    private String a_ex;
    private int a_type;
    private String a_date;
    private String a_marker;
    private String a_vedio1;
    private String a_vedio2;
    private String a_img1;
    private String a_img2;
    private String a_img3;
    private String a_img4;
    private String a_img5;
    private String a_img6;

    @Builder
    public ServiceZoneDto(Long pk, Long seq, String zonename, String zonecenter, String state, String address, String address1,
                          String ex, int type, String date, String marker, String vedio1, String vedio2,
                          String img1, String img2, String img3, String img4, String img5, String img6) {
        a_pk = pk;
        a_seq = seq;
        a_zonename = zonename;
        a_zonecenter = zonecenter;
        a_state = state;
        a_address = address;
        a_address1 = address1;
        a_ex = ex;
        a_type = type;
        a_date = date;
        a_marker = marker;
        a_vedio1 = vedio1;
        a_vedio2 = vedio2;
        a_img1 = img1;
        a_img2 = img2;
        a_img3 = img3;
        a_img4 = img4;
        a_img5 = img5;
        a_img6 = img6;
    }

    public ServiceZoneEntity toEntity(){
        ServiceZoneEntity entity = ServiceZoneEntity.builder()
                .pk(a_pk)
                .seq(a_seq)
                .zonename(a_zonename)
                .zonecenter(a_zonecenter)
                .state(a_state)
                .address(a_address)
                .address1(a_address1)
                .ex(a_ex)
                .type(a_type)
                .date(a_date)
                .marker(a_marker)
                .vedio1(a_vedio1)
                .vedio2(a_vedio2)
                .img1(a_img1)
                .img2(a_img2)
                .img3(a_img3)
                .img4(a_img4)
                .img5(a_img5)
                .img6(a_img6)
                .build();
        return entity;
    }
}

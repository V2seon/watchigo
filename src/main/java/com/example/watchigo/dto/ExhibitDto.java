package com.example.watchigo.dto;

import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExhibitDto {

    private Long a_seq;
    private Long a_pk;
    private Long a_userseq;
    private String a_type;
    private String a_typename;
    private String a_zonename;
    private String a_name;
    private String a_ex;
    private String a_point;
    private String a_video1;
    private String a_video2;
    private String a_img1;
    private String a_img2;
    private String a_img3;
    private String a_img4;
    private String a_img5;
    private String a_img6;
    private String a_mainicon;
    private String a_armarker;
    private int a_printtype;
    private String a_date;


    @Builder
    public ExhibitDto(Long seq,Long pk,Long userseq, String type, String typename, String zonename, String name, String ex, String point, String video1, String video2,
                          String img1, String img2, String img3, String img4, String img5, String img6,
                          String mainicon,String armarker, int printtype ,String date) {
        a_seq = seq;
        a_pk = pk;
        a_userseq = userseq;
        a_type = type;
        a_typename = typename;
        a_zonename = zonename;
        a_name = name;
        a_ex = ex;
        a_point = point;
        a_video1 = video1;
        a_video2 = video2;
        a_img1 = img1;
        a_img2 = img2;
        a_img3 = img3;
        a_img4 = img4;
        a_img5 = img5;
        a_img6 = img6;
        a_mainicon = mainicon;
        a_armarker = armarker;
        a_printtype = printtype;
        a_date = date;
    }

    public ExhibitEntity toEntity(){
        ExhibitEntity entity = ExhibitEntity.builder()
                .seq(a_seq)
                .pk(a_pk)
                .userseq(a_userseq)
                .type(a_type)
                .typename(a_typename)
                .zonename(a_zonename)
                .name(a_name)
                .ex(a_ex)
                .point(a_point)
                .video1(a_video1)
                .video2(a_video2)
                .img1(a_img1)
                .img2(a_img2)
                .img3(a_img3)
                .img4(a_img4)
                .img5(a_img5)
                .img6(a_img6)
                .mainicon(a_mainicon)
                .armarker(a_armarker)
                .printtype(a_printtype)
                .date(a_date)
                .build();
        return entity;
    }


}

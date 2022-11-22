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
    private String a_name;
    private String a_ex;
    private String a_vedio1;
    private String a_vedio2;
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
    public ExhibitDto(Long pk, Long seq,Long userseq, String type, String name, String ex, String vedio1, String vedio2,
                          String img1, String img2, String img3, String img4, String img5, String img6,
                          String mainicon,String armarker, int printtype ,String date) {
        a_seq = seq;
        a_pk = pk;
        a_userseq = userseq;
        a_type = type;
        a_name = name;
        a_ex = ex;
        a_vedio1 = vedio1;
        a_vedio2 = vedio2;
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
                .seq(a_pk)
                .pk(a_seq)
                .userseq(a_userseq)
                .type(a_type)
                .name(a_name)
                .ex(a_ex)
                .vedio1(a_vedio1)
                .vedio2(a_vedio2)
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

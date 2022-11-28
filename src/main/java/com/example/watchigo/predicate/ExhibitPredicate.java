package com.example.watchigo.predicate;

import com.example.watchigo.entity.QExhibitEntity;
import com.querydsl.core.BooleanBuilder;

public class ExhibitPredicate {

    public static BooleanBuilder search(String selectKey, String titleText, Long seq){
        QExhibitEntity qExhibitEntity = QExhibitEntity.exhibitEntity;

        BooleanBuilder builder = new BooleanBuilder();

        if(!selectKey.equals("전체")){
            if(selectKey.equals("번호")){
                try{
                    builder.and(qExhibitEntity.pk.eq(Long.valueOf(titleText))).and
                            (qExhibitEntity.userseq.eq(seq));
                }
                catch (Exception e){
//                    builder.and(qServiceZoneEntity.zonename.contains(titleText));
                    System.out.println("접수번호로 조회할 경우 String 타입으로 검색하셔야 합니다.");
                }
            }
            else if(selectKey.equals("서비스존")){
                builder.and(qExhibitEntity.zonename.contains(titleText)).and
                        (qExhibitEntity.userseq.eq(seq));
            }
            else if(selectKey.equals("분류")){
                builder.and(qExhibitEntity.type.contains(titleText)).and
                        (qExhibitEntity.userseq.eq(seq));

            }
            else if(selectKey.equals("전시/시설물")){
                builder.and(qExhibitEntity.name.contains(titleText)).and
                        (qExhibitEntity.userseq.eq(seq));
            }
        }
        else if(selectKey.equals("전체")){
                builder.and(
                        (qExhibitEntity.zonename.contains(titleText)).or
                                (qExhibitEntity.type.contains(titleText)).or
                                (qExhibitEntity.name.contains(titleText))).and
                                (qExhibitEntity.userseq.eq(seq));
        }

//        builder.and(qPersonalDataEntity.aname.eq(titleText));

        return builder;
    }
}

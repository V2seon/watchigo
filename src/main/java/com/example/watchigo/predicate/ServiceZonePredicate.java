package com.example.watchigo.predicate;

import com.example.watchigo.entity.QServiceZoneEntity;
import com.querydsl.core.BooleanBuilder;

public class ServiceZonePredicate {

    public static BooleanBuilder search(String selectKey, String titleText, Long seq){
        QServiceZoneEntity qServiceZoneEntity = QServiceZoneEntity.serviceZoneEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);

        if(!selectKey.equals("전체")){
            if(selectKey.equals("번호")){
                try{
                    builder.and(qServiceZoneEntity.pk.eq(Long.valueOf(titleText))).and
                            (qServiceZoneEntity.seq.eq(seq));
                }
                catch (Exception e){
//                    builder.and(qServiceZoneEntity.zonename.contains(titleText));
                    System.out.println("접수번호로 조회할 경우 String 타입으로 검색하셔야 합니다.");
                }
            }
            else if(selectKey.equals("이름")){
                builder.and(qServiceZoneEntity.zonename.contains(titleText)).and
                        (qServiceZoneEntity.seq.eq(seq));
            }
            else if(selectKey.equals("상태")){
                builder.and(qServiceZoneEntity.state.contains(titleText)).and
                        (qServiceZoneEntity.seq.eq(seq));
            }
            else if(selectKey.equals("주소")){
                builder.and(qServiceZoneEntity.address.contains(titleText)).and
                        (qServiceZoneEntity.seq.eq(seq));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qServiceZoneEntity.pk.eq(Long.valueOf(titleText))).and
                        (qServiceZoneEntity.seq.eq(seq));
            }
            catch (Exception e){
                builder.and(
                        (qServiceZoneEntity.zonename.contains(titleText)).or
                                (qServiceZoneEntity.address.contains(titleText)).or
                                (qServiceZoneEntity.state.contains(titleText))).and
                                (qServiceZoneEntity.seq.eq(seq));
            }

        }

//        builder.and(qPersonalDataEntity.aname.eq(titleText));

        return builder;
    }
}

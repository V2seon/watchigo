package com.example.watchigo.entity;

import com.example.watchigo.dto.RectangleDto;
import com.example.watchigo.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "rectangle")
public class RentangleEntiry {

    @Id
    @Column(name = "pk")
    private Long apk;

    @Column(name = "date")
    private String adate;

    @Column(name = "spoint")
    private String aspoint;

    @Column(name = "epoint")
    private String aepoint;

    @Builder
    public RentangleEntiry(Long a_pk, String a_date, String a_spoint, String a_epoint) {
        this.apk = a_pk;
        this.adate = a_date;
        this.aspoint = a_spoint;
        this.aepoint = a_epoint;
    }

    public static RentangleEntiry toSaveEntity (RectangleDto RectangleDto){
        RentangleEntiry rentangleEntiry = new RentangleEntiry();
        rentangleEntiry.setApk(RectangleDto.getA_PK());
        rentangleEntiry.setAdate(RectangleDto.getA_DATE());
        rentangleEntiry.setAspoint(RectangleDto.getA_SPOINT());
        rentangleEntiry.setAepoint(RectangleDto.getA_EPOINT());
        return rentangleEntiry;
    }
}

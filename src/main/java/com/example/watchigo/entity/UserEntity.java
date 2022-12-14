package com.example.watchigo.entity;

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
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long aseq;

    @Column(name = "id")
    private String aid;

    @Column(name = "pw")
    private String apw;

    @Column(name = "name")
    private String aname;

    @Column(name = "date")
    private String adate;

    @Column(name = "phone")
    private String aphone;

    @Column(name = "email")
    private String aemail;

    @Builder
    public UserEntity(Long a_seq, String a_id, String a_pw, String a_date, String a_name, String a_phone, String a_email) {
        this.aseq = a_seq;
        this.aid = a_id;
        this.apw = a_pw;
        this.aname = a_name;
        this.adate = a_date;
        this.aphone =a_phone;
        this.aemail = a_email;
    }

    public static UserEntity toSaveEntity (UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setAseq(userDto.getA_SEQ());
        userEntity.setAid(userDto.getA_PHONE());
        userEntity.setApw(userDto.getA_PW());
        userEntity.setAname(userDto.getA_NAME());
        userEntity.setAdate(userDto.getA_DATE());
        userEntity.setAphone(userDto.getA_PHONE());
        userEntity.setAemail(userDto.getA_EMAIL());
        return userEntity;
    }


}

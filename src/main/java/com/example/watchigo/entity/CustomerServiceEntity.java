package com.example.watchigo.entity;

import com.example.watchigo.dto.CustomerServiceDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "customerservice")
public class CustomerServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Long apk;

    @Column(name = "seq")
    private Long aseq;

    @Column(name = "type")
    private String atype;

    @Column(name = "title")
    private String atitle;

    @Column(name = "text")
    private String atext;

    @Column(name = "name")
    private String aname;

    @Column(name = "date")
    private String adate;

    @Column(name = "state")
    private String astate;

    @Builder
    public CustomerServiceEntity(Long a_pk, Long a_seq, String a_type, String a_title, String a_date, String a_name,
                                 String a_text, String a_state) {
        this.apk = a_pk;
        this.aseq = a_seq;
        this.atype = a_type;
        this.atitle = a_title;
        this.atext = a_text;
        this.aname = a_name;
        this.adate =a_date;
        this.astate = a_state;
    }

    public static CustomerServiceEntity toSaveEntity (CustomerServiceDto customerServiceDto){
        CustomerServiceEntity customerServiceEntity = new CustomerServiceEntity();
        customerServiceEntity.setApk(customerServiceDto.getA_PK());
        customerServiceEntity.setAseq(customerServiceDto.getA_SEQ());
        customerServiceEntity.setAtype(customerServiceDto.getA_TYPE());
        customerServiceEntity.setAtitle(customerServiceDto.getA_TITLE());
        customerServiceEntity.setAtext(customerServiceDto.getA_TEXT());
        customerServiceEntity.setAname(customerServiceDto.getA_NAME());
        customerServiceEntity.setAdate(customerServiceDto.getA_DATE());
        customerServiceEntity.setAstate(customerServiceDto.getA_STATE());
        return customerServiceEntity;
    }


}

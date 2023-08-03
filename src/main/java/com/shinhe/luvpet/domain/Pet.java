package com.shinhe.luvpet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Pet {
    @Id
    String desertionNo;

    String filename;
    String happenDt;
    String happenPlace;
    String kindCd;
    String colorCd;
    int age;
    Double weight;
    String noticeNo;
    String noticeSdt;
    String noticeEdt;
    String popfile;
    String processState;
    String sexCd;
    String neuterYn;
    String specialMark;
    String careNm;
    String careTel;
    String careAddr;
    String orgNm;
    String chargeNm;
    String officetel;
    String noticeComment;

}

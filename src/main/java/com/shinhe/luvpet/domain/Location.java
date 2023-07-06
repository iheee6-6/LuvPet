package com.shinhe.luvpet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Location {

    @Id
    private int orgCd;//시군구코드
    private int uprCd; //시군구상위코드
    private String orgdownNm; //시군구명

}

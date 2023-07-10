package com.shinhe.luvpet.dto;

import lombok.Getter;

@Getter
public class LocationResponseDto {

    private int uprCd;
    private int orgCd;//시군구코드
    private String orgdownNm; //시군구명

    public LocationResponseDto(int orgCd, String orgdownNm){
        this.orgCd=orgCd;
        this.orgdownNm=orgdownNm;
    }


    public LocationResponseDto(int uprCd, int orgCd, String orgdownNm){
        this.uprCd=uprCd;
        this.orgCd=orgCd;
        this.orgdownNm=orgdownNm;
    }
}

package com.shinhe.luvpet.dto;

import lombok.Getter;

@Getter
public class SidoResponseDto {

    private int orgCd;//시군구코드
    private String orgdownNm; //시군구명

    public SidoResponseDto(int orgCd, String orgdownNm){
        this.orgCd=orgCd;
        this.orgdownNm=orgdownNm;
    }
}

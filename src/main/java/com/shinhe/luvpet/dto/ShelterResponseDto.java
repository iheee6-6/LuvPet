package com.shinhe.luvpet.dto;

import lombok.Getter;

@Getter
public class ShelterResponseDto {
    private long careRegNo;
    private String careNm;

    public ShelterResponseDto(long careRegNo, String careNm) {
        this.careRegNo = careRegNo;
        this.careNm = careNm;
    }
}

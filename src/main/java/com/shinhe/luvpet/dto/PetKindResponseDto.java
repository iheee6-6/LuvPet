package com.shinhe.luvpet.dto;

import lombok.Getter;

@Getter
public class PetKindResponseDto {
    private String kindCd;
    private String KNm;

    public PetKindResponseDto(String kindCd, String KNm) {
        this.kindCd = kindCd;
        this.KNm = KNm;
    }
}

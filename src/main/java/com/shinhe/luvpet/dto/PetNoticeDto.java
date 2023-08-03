package com.shinhe.luvpet.dto;

import lombok.Getter;

@Getter
public class PetNoticeDto {
    private String desertionNo,
            filename, happenDt, happenPlace,
            kindCd,
            colorCd,
            age,
            weight,
            noticeNo,
            noticeSdt,
            noticeEdt,
            popfile,
            processState,
            sexCd,
            neuterYn,
            specialMark,
            careNm,
            careTel,
            careAddr,
            orgNm,
            chargeNm,
            officetel;

    public PetNoticeDto(String desertionNo, String filename, String happenDt, String happenPlace, String kindCd, String colorCd, String age, String weight, String noticeNo, String noticeSdt, String noticeEdt, String popfile, String processState, String sexCd, String neuterYn, String specialMark, String careNm, String careTel, String careAddr, String orgNm, String chargeNm, String officetel) {
        this.desertionNo = desertionNo;
        this.filename = filename;
        this.happenDt = happenDt;
        this.happenPlace = happenPlace;
        this.kindCd = kindCd;
        this.colorCd = colorCd;
        this.age = age;
        this.weight = weight;
        this.noticeNo = noticeNo;
        this.noticeSdt = noticeSdt;
        this.noticeEdt = noticeEdt;
        this.popfile = popfile;
        this.processState = processState;
        this.sexCd = sexCd;
        this.neuterYn = neuterYn;
        this.specialMark = specialMark;
        this.careNm = careNm;
        this.careTel = careTel;
        this.careAddr = careAddr;
        this.orgNm = orgNm;
        this.chargeNm = chargeNm;
        this.officetel = officetel;
    }
}

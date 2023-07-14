package com.shinhe.luvpet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PetKind {
    @Id
    private String kindCd;
    private String KNm;

    private String uprType;
}

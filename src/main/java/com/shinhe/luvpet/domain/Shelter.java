package com.shinhe.luvpet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Shelter {
    @Id
    private long careRegNo;
    private String careNm;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orgCd")
    private Location location;
}

package com.shinhe.luvpet.service;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.dto.SidoResponseDto;
import com.shinhe.luvpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SidoService {

    private final PetRepository petRepository;

    @Transactional
    public void saveSido(List<SidoResponseDto> sidoResponseDtos) {
        for(SidoResponseDto srd : sidoResponseDtos){
            Location sd = new Location();
            sd.setOrgCd(srd.getOrgCd());
            sd.setOrgdownNm(srd.getOrgdownNm());
            System.out.println("srd: " + srd);
            petRepository.save(sd);
        }
    }
}

package com.shinhe.luvpet.service;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.dto.LocationResponseDto;
import com.shinhe.luvpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final PetRepository petRepository;

    @Transactional
    public void saveSido(List<LocationResponseDto> locationResponseDtos) {
        for(LocationResponseDto srd : locationResponseDtos){
            Location sd = new Location();
            sd.setUprCd(srd.getUprCd());
            sd.setOrgCd(srd.getOrgCd());
            sd.setOrgdownNm(srd.getOrgdownNm());
            System.out.println("srd: " + srd);
            petRepository.save(sd);
        }
    }
}

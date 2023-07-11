package com.shinhe.luvpet.service;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.domain.Shelter;
import com.shinhe.luvpet.dto.ShelterResponseDto;
import com.shinhe.luvpet.repository.LocationRepository;
import com.shinhe.luvpet.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final LocationRepository locationRepository;
    private final ShelterRepository shelterRepository;

    @Transactional
    public void saveShelter(List<ShelterResponseDto> shelterResponseDtos,int uprCd) {
        for(ShelterResponseDto srd : shelterResponseDtos){
            Location location = locationRepository.findOne(uprCd);
            Shelter sl = new Shelter();
            sl.setCareRegNo(srd.getCareRegNo());
            sl.setCareNm(srd.getCareNm());
            sl.setLocation(location);
            shelterRepository.save(sl);
        }
    }
}

package com.shinhe.luvpet.service;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.dto.LocationResponseDto;
import com.shinhe.luvpet.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public void saveSido(List<LocationResponseDto> locationResponseDtos) {
        for(LocationResponseDto srd : locationResponseDtos){
            Location sd = new Location();
            sd.setUprCd(srd.getUprCd());
            sd.setOrgCd(srd.getOrgCd());
            sd.setOrgdownNm(srd.getOrgdownNm());
            locationRepository.save(sd);
        }
    }
}

package com.shinhe.luvpet.service;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.domain.PetKind;
import com.shinhe.luvpet.domain.Shelter;
import com.shinhe.luvpet.dto.PetKindResponseDto;
import com.shinhe.luvpet.dto.ShelterResponseDto;
import com.shinhe.luvpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public void savePetKind(List<PetKindResponseDto> PetKindResponseDtos, String uprType) {
        for(PetKindResponseDto pkd : PetKindResponseDtos){
            PetKind pk = new PetKind();
            pk.setKindCd(pkd.getKindCd());
            pk.setUprType(uprType);
            pk.setKNm(pkd.getKNm());
            petRepository.save(pk);
        }
    }
}

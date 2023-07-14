package com.shinhe.luvpet.repository;

import com.shinhe.luvpet.domain.PetKind;
import com.shinhe.luvpet.domain.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PetRepository {
    private final EntityManager em;

    public void save(PetKind petKind){
        em.persist(petKind);
    }
}

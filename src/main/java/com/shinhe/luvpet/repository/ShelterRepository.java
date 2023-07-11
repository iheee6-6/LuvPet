package com.shinhe.luvpet.repository;

import com.shinhe.luvpet.domain.Location;
import com.shinhe.luvpet.domain.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ShelterRepository {
    private final EntityManager em;

    public void save(Shelter shelter){
        em.persist(shelter);
    }
}

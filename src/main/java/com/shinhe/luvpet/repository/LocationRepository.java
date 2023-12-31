package com.shinhe.luvpet.repository;

import com.shinhe.luvpet.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LocationRepository {

    private final EntityManager em;

    public void save(Location sido){
        em.persist(sido);
    }

    public Location findOne(int orgCd) {
        return em.find(Location.class, orgCd);
    }
}

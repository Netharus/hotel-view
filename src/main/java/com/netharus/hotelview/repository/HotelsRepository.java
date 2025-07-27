package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsRepository extends JpaRepository<Hotels, Long>, JpaSpecificationExecutor<Hotels> {

    Boolean existsByNameIgnoreCase(String name);

}

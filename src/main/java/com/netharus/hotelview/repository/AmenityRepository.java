package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findByNameInIgnoreCase(List<String> names);
}

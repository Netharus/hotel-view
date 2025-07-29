package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.dto.response.HistogramCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findByNameInIgnoreCase(List<String> names);

    @Query("SELECT new com.netharus.hotelview.dto.response.HistogramCount(a.name,COUNT(a)) "
            + "FROM Amenity a GROUP BY a.name")
    List<HistogramCount> countGroupedByAmenity();
}

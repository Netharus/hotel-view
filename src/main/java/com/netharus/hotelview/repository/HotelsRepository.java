package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.response.HistogramCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelsRepository extends JpaRepository<Hotels, Long>, JpaSpecificationExecutor<Hotels> {

    Boolean existsByNameIgnoreCase(String name);

    @Query("SELECT new com.netharus.hotelview.dto.response.HistogramCount(h.brand,COUNT(h)) "
            + "FROM Hotels h GROUP BY h.brand")
    List<HistogramCount> countGroupedByBrand();

    @Query("SELECT new com.netharus.hotelview.dto.response.HistogramCount(a.name, COUNT(h)) "
            + "FROM Hotels h JOIN h.amenities a GROUP BY a.name")
    List<HistogramCount> countGroupedByAmenity();
}

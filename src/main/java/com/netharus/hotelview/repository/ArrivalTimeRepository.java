package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.ArrivalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime, Long> {
}

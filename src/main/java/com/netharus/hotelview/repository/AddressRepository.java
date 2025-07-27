package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Address;
import com.netharus.hotelview.dto.response.HistogramCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsAddressByHouseNumberAndStreetAndCityAndCountryAndPostCodeIgnoreCase(Integer houseNumber,
                                                                                      String street,
                                                                                      String city,
                                                                                      String country,
                                                                                      String postCode);

    @Query("SELECT new com.netharus.hotelview.dto.response.HistogramCount(a.city,COUNT(a)) "
            + "FROM Address a GROUP BY a.city")
    List<HistogramCount> countGroupedByCity();

    @Query("SELECT new com.netharus.hotelview.dto.response.HistogramCount(a.country,COUNT(a)) "
            + "FROM Address a GROUP BY a.country")
    List<HistogramCount> countGroupedByCountry();
}

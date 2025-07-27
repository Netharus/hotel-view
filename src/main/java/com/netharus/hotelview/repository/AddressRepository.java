package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsAddressByHouseNumberAndStreetAndCityAndCountryAndPostCodeIgnoreCase(Integer houseNumber,
                                                                                      String street,
                                                                                      String city,
                                                                                      String country,
                                                                                      String postCode);

}

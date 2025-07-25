package com.netharus.hotelview.mapper;

import com.netharus.hotelview.domain.Address;
import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.response.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelMapper {

    List<HotelDto> fromHotelListToHotelDtoList(List<Hotels> hotelsList);

    @Mapping(target = "phone", source = "hotels.contacts.phone")
    @Mapping(target = "address", source = "address", qualifiedByName = "addressToString")
    HotelDto fromHotelToHotelDto(Hotels hotels);

    @Named("addressToString")
    default String addressToString(Address address) {
        return String.format("%s %s, %s, %s, %s", address.getHouseNumber(), address.getStreet(), address.getCity(),
                address.getPostCode(), address.getCountry());
    }
}

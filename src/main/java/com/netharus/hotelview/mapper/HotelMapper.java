package com.netharus.hotelview.mapper;

import com.netharus.hotelview.domain.Address;
import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelMapper {

    @Mapping(target = "amenities", source = "amenities", qualifiedByName = "amenitiesToStringList")
    FullHotelResponseDto fromHotelToFullHotelDto(Hotels hotels);

    List<HotelResponseDto> fromHotelListToHotelDtoList(List<Hotels> hotelsList);

    @Mapping(target = "phone", source = "hotels.contacts.phone")
    @Mapping(target = "address", source = "address", qualifiedByName = "addressToString")
    HotelResponseDto fromHotelToHotelDto(Hotels hotels);

    @Named("addressToString")
    default String addressToString(Address address) {
        return String.format("%s %s, %s, %s, %s", address.getHouseNumber(), address.getStreet(), address.getCity(),
                address.getPostCode(), address.getCountry());
    }

    @Named("amenitiesToStringList")
    default List<String> amenitiesToStringList(Set<Amenity> amenitySet) {
        return amenitySet.stream().map(Amenity::getName).toList();
    }
}

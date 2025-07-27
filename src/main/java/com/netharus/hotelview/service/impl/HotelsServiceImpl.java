package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.request.HotelCreateDto;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.exceptions.ErrorMessages;
import com.netharus.hotelview.exceptions.HotelNotFoundException;
import com.netharus.hotelview.mapper.HotelMapper;
import com.netharus.hotelview.repository.HotelsRepository;
import com.netharus.hotelview.service.HotelsService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelsServiceImpl implements HotelsService {

    private final HotelsRepository hotelsRepository;

    private final HotelMapper hotelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponseDto> getHotelDtoList() {
        return hotelMapper.fromHotelListToHotelDtoList(hotelsRepository.findAll());
    }

    @Override
    @Transactional
    public FullHotelResponseDto findHotelById(Long id) {
        return hotelsRepository.findById(id).map(hotelMapper::fromHotelToFullHotelDto)
                .orElseThrow(() -> new HotelNotFoundException(String.format(ErrorMessages.HOTEL_NOT_FOUND, id)));
    }

    @Override
    public List<HotelResponseDto> searchHotels(String name, String brand, String city, String country,
                                               List<String> amenities) {
        Specification<Hotels> specs = (root, query, cb) -> cb.conjunction();
        specs = specs.and(likeName(name));
        specs = specs.and(likeBrand(brand));
        specs = specs.and(likeCity(city));
        specs = specs.and(likeCountry(country));
        specs = specs.and(hasAmenitiesLike(amenities));
        return hotelMapper.fromHotelListToHotelDtoList(hotelsRepository.findAll(specs));
    }

    @Override
    public HotelResponseDto add(HotelCreateDto hotelCreateDto) {
        Hotels hotel = hotelMapper.fromHotelCreateDtoToHotel(hotelCreateDto);
        return hotelMapper.fromHotelToHotelDto(hotelsRepository.save(hotel));
    }

    @Override
    public void addAmenitiesById(Long hotelId, List<Amenity> amenities) {
        Hotels hotel = hotelsRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(String.format(ErrorMessages.HOTEL_NOT_FOUND, hotelId)));
        amenities.forEach(amenity -> hotel.getAmenities().add(amenity));
        hotelsRepository.save(hotel);
    }


    private Specification<Hotels> likeName(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")),
                "%" + name.toLowerCase() + "%");
    }

    private Specification<Hotels> likeBrand(String brand) {
        if (brand == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("brand")),
                "%" + brand + "%");
    }

    private Specification<Hotels> likeCity(String city) {
        if (city == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("address").get("city")),
                "%" + city.toLowerCase() + "%");
    }

    private Specification<Hotels> likeCountry(String country) {
        if (country == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("address").get("country")),
                "%" + country.toLowerCase() + "%");
    }

    private Specification<Hotels> hasAmenitiesLike(List<String> amenities) {
        if (amenities == null || amenities.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            Join<Hotels, Amenity> amenityJoin = root.join("amenities", JoinType.INNER);
            List<Predicate> likePredicates = new ArrayList<>();

            for (String amenity : amenities) {
                likePredicates.add(cb.like(cb.lower(amenityJoin.get("name")), "%" + amenity.toLowerCase() + "%"));
            }

            query.distinct(true);

            return cb.or(likePredicates.toArray(new Predicate[0]));
        };
    }


}

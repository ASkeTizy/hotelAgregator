package o.e.app.service;

import lombok.RequiredArgsConstructor;
import o.e.api.model.HotelBrief;
import o.e.api.model.HotelCreate;
import o.e.api.model.HotelDetailed;
import o.e.app.entity.HotelEntity;
import o.e.app.mapper.HotelMapper;
import o.e.app.repository.HotelRepository;
import o.e.app.repository.HotelSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;



    public List<HotelBrief> getAllHotels() {
        return hotelRepository.findAll().stream()
            .map(hotelMapper::toHotelBrief)
            .collect(Collectors.toList());
    }

    public HotelDetailed getHotelById(Long id) {
        return hotelRepository.findById(id)
            .map(hotelMapper::toHotelDetailed)
            .orElse(null);
    }

    public HotelBrief createHotel(HotelCreate hotelCreate) {
        HotelEntity entity = hotelMapper.toHotelEntity(hotelCreate);
        HotelEntity savedEntity = hotelRepository.save(entity);
        return hotelMapper.toHotelBrief(savedEntity);
    }

    public void addAmenitiesToHotel(Long id, List<String> amenities) {
        HotelEntity entity = hotelRepository.findById(id).orElse(null);
        if (entity != null) {
            if (entity.getAmenities() == null) {
                entity.setAmenities(amenities);
            } else {
                entity.getAmenities().addAll(amenities);
            }
            hotelRepository.save(entity);
        }
    }

    public List<HotelBrief> searchHotels(String name, String brand, String city, String country, String amenities) {
        Specification<HotelEntity> spec = HotelSpecification.searchHotels(name, brand, city, country, amenities);
        List<HotelEntity> result = hotelRepository.findAll(spec);
        return result.stream()
            .map(hotelMapper::toHotelBrief)
            .collect(Collectors.toList());
    }

    public Map<String, Integer> getHistogram(String param) {
        return switch (param.toLowerCase()) {
            case "brand" -> hotelRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        HotelEntity::getBrand,
                    Collectors.summingInt(hotel -> 1)
                ));
            case "city" -> hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getAddress() != null && hotel.getAddress().getCity() != null)
                .collect(Collectors.groupingBy(
                    hotel -> hotel.getAddress().getCity(),
                    Collectors.summingInt(hotel -> 1)
                ));
            case "country" -> hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getAddress() != null && hotel.getAddress().getCountry() != null)
                .collect(Collectors.groupingBy(
                    hotel -> hotel.getAddress().getCountry(),
                    Collectors.summingInt(hotel -> 1)
                ));
            case "amenities" -> hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getAmenities() != null)
                .flatMap(hotel -> hotel.getAmenities().stream())
                .collect(Collectors.groupingBy(
                    amenity -> amenity,
                    Collectors.summingInt(amenity -> 1)
                ));
            default -> throw new IllegalArgumentException("Invalid parameter: " + param);
        };
    }
}

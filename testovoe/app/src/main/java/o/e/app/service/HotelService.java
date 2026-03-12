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

import java.util.ArrayList;
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
                entity.setAmenities(new ArrayList<>(amenities));
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
        List<Object[]> result = switch (param.toLowerCase()) {
            case "brand" -> hotelRepository.countByBrand();
            case "city" -> hotelRepository.countByCity();
            case "country" -> hotelRepository.countByCountry();
            case "amenities" -> hotelRepository.countByAmenities();
            default -> throw new IllegalArgumentException("Invalid parameter: " + param);
        };
        return result.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).intValue(), // Используем Number для безопасности
                        (existing, replacement) -> existing
                ));
    };
    }




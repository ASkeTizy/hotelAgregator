package o.e.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import o.e.api.HistogramApi;
import o.e.api.HotelsApi;
import o.e.api.SearchApi;
import o.e.api.model.HotelBrief;
import o.e.api.model.HotelCreate;
import o.e.api.model.HotelDetailed;
import o.e.app.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HotelsController implements HotelsApi, SearchApi, HistogramApi {
    
    private final HotelService hotelService;

    @Override
    public ResponseEntity<Void> addAmenitiesToHotel(Long id, List<String> requestBody) {
        hotelService.addAmenitiesToHotel(id, requestBody);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<HotelBrief> createHotel(HotelCreate hotelCreate) {
        HotelBrief hotel = hotelService.createHotel(hotelCreate);
        return ResponseEntity.ok(hotel);
    }

    @Override
    public ResponseEntity<HotelDetailed> getHotelById(Long id) {
        HotelDetailed hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @Override
    public ResponseEntity<List<HotelBrief>> getHotels() {
        List<HotelBrief> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @Override
    public ResponseEntity<List<HotelBrief>> searchHotels(String name, String brand, String city, String country, String amenities) {
        List<HotelBrief> hotels = hotelService.searchHotels(name, brand, city, country, amenities);
        return ResponseEntity.ok(hotels);
    }

    @Override
    public ResponseEntity<Map<String, Integer>> getHistogram(String param) {
        Map<String, Integer> histogram = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogram);
    }
}

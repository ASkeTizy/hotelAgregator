package o.e.app.service;

import o.e.api.model.HotelBrief;
import o.e.api.model.HotelCreate;
import o.e.api.model.HotelDetailed;
import o.e.app.entity.HotelEntity;
import o.e.app.mapper.HotelMapper;
import o.e.app.repository.HotelRepository;
import o.e.app.repository.HotelSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelService hotelService;

    private HotelEntity hotelEntity;
    private HotelDetailed hotelDetailed;
    private HotelBrief hotelBrief;
    private HotelCreate hotelCreate;

    @BeforeEach
    void setUp() {
        hotelEntity = new HotelEntity();
        hotelEntity.setId(1L);
        hotelEntity.setName("Test Hotel");
        hotelEntity.setDescription("Test Description");
        hotelEntity.setBrand("Test Brand");

        hotelDetailed = new HotelDetailed();
        hotelDetailed.setId(1L);
        hotelDetailed.setName("Test Hotel");
        hotelDetailed.setDescription("Test Description");
        hotelDetailed.setBrand("Test Brand");

        hotelBrief = new HotelBrief();
        hotelBrief.setId(1L);
        hotelBrief.setName("Test Hotel");
        hotelBrief.setDescription("Test Description");
        hotelBrief.setAddress("Test Address");
        hotelBrief.setPhone("+123456789");

        hotelCreate = new HotelCreate();
        hotelCreate.setName("New Hotel");
        hotelCreate.setDescription("New Description");
        hotelCreate.setBrand("New Brand");
    }

    @Test
    void getAllHotels_shouldReturnListOfHotelBrief() {
        // Given
        List<HotelEntity> hotelEntities = Arrays.asList(hotelEntity);
        when(hotelRepository.findAll()).thenReturn(hotelEntities);
        when(hotelMapper.toHotelBrief(any(HotelEntity.class))).thenReturn(hotelBrief);

        // When
        List<HotelBrief> result = hotelService.getAllHotels();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Hotel", result.get(0).getName());
        verify(hotelRepository, times(1)).findAll();
        verify(hotelMapper, times(1)).toHotelBrief(any(HotelEntity.class));
    }

    @Test
    void getHotelById_shouldReturnHotelDetailed_whenHotelExists() {
        // Given
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotelEntity));
        when(hotelMapper.toHotelDetailed(any(HotelEntity.class))).thenReturn(hotelDetailed);

        // When
        HotelDetailed result = hotelService.getHotelById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Test Hotel", result.getName());
        verify(hotelRepository, times(1)).findById(1L);
        verify(hotelMapper, times(1)).toHotelDetailed(any(HotelEntity.class));
    }

    @Test
    void getHotelById_shouldReturnNull_whenHotelDoesNotExist() {
        // Given
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        HotelDetailed result = hotelService.getHotelById(1L);

        // Then
        assertNull(result);
        verify(hotelRepository, times(1)).findById(1L);
        verify(hotelMapper, never()).toHotelDetailed(any(HotelEntity.class));
    }

    @Test
    void createHotel_shouldReturnHotelBrief() {
        // Given
        HotelEntity savedEntity = new HotelEntity();
        savedEntity.setId(1L);
        savedEntity.setName("New Hotel");

        when(hotelMapper.toHotelEntity(any(HotelCreate.class))).thenReturn(hotelEntity);
        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(savedEntity);
        when(hotelMapper.toHotelBrief(any(HotelEntity.class))).thenReturn(hotelBrief);

        // When
        HotelBrief result = hotelService.createHotel(hotelCreate);

        // Then
        assertNotNull(result);
        assertEquals("Test Hotel", result.getName());
        verify(hotelMapper, times(1)).toHotelEntity(any(HotelCreate.class));
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
        verify(hotelMapper, times(1)).toHotelBrief(any(HotelEntity.class));
    }

    @Test
    void addAmenitiesToHotel_shouldAddAmenities_whenHotelExists() {
        // Given
        List<String> amenities = Arrays.asList("WiFi", "Parking");
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotelEntity));

        // When
        hotelService.addAmenitiesToHotel(1L, amenities);

        // Then
        verify(hotelRepository, times(1)).findById(1L);
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
        assertNotNull(hotelEntity.getAmenities());
        assertTrue(hotelEntity.getAmenities().containsAll(amenities));
    }

    @Test
    void addAmenitiesToHotel_shouldNotSave_whenHotelDoesNotExist() {
        // Given
        List<String> amenities = Arrays.asList("WiFi", "Parking");
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        hotelService.addAmenitiesToHotel(1L, amenities);

        // Then
        verify(hotelRepository, times(1)).findById(1L);
        verify(hotelRepository, never()).save(any(HotelEntity.class));
    }

    @Test
    void searchHotels_shouldReturnListOfHotelBrief() {
        // Given
        List<HotelEntity> hotelEntities = Arrays.asList(hotelEntity);
        when(hotelRepository.findAll(any(Specification.class))).thenReturn(hotelEntities);
        when(hotelMapper.toHotelBrief(any(HotelEntity.class))).thenReturn(hotelBrief);

        // When
        List<HotelBrief> result = hotelService.searchHotels("Test", "Brand", "City", "Country", "WiFi");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Hotel", result.get(0).getName());
        verify(hotelRepository, times(1)).findAll(any(Specification.class));
        verify(hotelMapper, times(1)).toHotelBrief(any(HotelEntity.class));
    }

    @Test
    void getHistogram_shouldReturnBrandHistogram() {
        // Given
        List<Object[]> mockDbResult = Collections.singletonList(new Object[]{"Hilton", 1L});

        // Мокаем именно тот метод, который вызывается в switch-case!
        when(hotelRepository.countByBrand()).thenReturn(mockDbResult);

        // When
        Map<String, Integer> result = hotelService.getHistogram("brand");

        // Then
        assertEquals(1, result.get("Hilton"));
    }

    @Test
    void getHistogram_shouldReturnAmenitiesHistogram() {
        Object[] row1 = new Object[]{"WiFi", 2L};
        Object[] row2 = new Object[]{"Parking", 1L};

        List<Object[]> mockDbResult = Arrays.asList(row1, row2);

        // 2. Мокаем метод, который реально вызывается в switch-case
        when(hotelRepository.countByAmenities()).thenReturn(mockDbResult);

        // 3. Вызываем сервис
        Map<String, Integer> result = hotelService.getHistogram("amenities");

        // 4. Проверяем
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(2, result.get("WiFi"));
        assertEquals(1, result.get("Parking"));
    }

    @Test
    void getHistogram_shouldThrowIllegalArgumentException_forInvalidParameter() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            hotelService.getHistogram("invalid");
        });
    }
}

package o.e.app.service;

import liquibase.integration.spring.SpringLiquibase;
import o.e.api.model.HotelBrief;
import o.e.api.model.HotelCreate;
import o.e.app.entity.AddressEntity;
import o.e.app.entity.HotelEntity;
import o.e.app.repository.HotelRepository;
import o.e.app.mapper.HotelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"local", "h2"})
@Import(HotelServiceIntegrationTest.TestConfig.class)
// Загружает контекст приложения
@Transactional  // Откатывает изменения в БД после каждого теста
public class HotelServiceIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    void shouldSaveAndRetrieveHotelFromH2() {
        // 1. Создаем сущность напрямую в БД через репозиторий
        HotelEntity entity = new HotelEntity();
        entity.setName("Real H2 Hotel");
        entity.setBrand("Testing");
        hotelRepository.save(entity);

        // 2. Вызываем сервис, чтобы проверить, видит ли он данные в БД
        var result = hotelService.getAllHotels();

        // 3. Проверяем
        assertFalse(result.isEmpty());
        assertEquals("Real H2 Hotel", result.get(0).getName());
    }
    @Test
    void shouldCreateHotelWithComplexFields() {
        // Given
        HotelCreate dto = new HotelCreate();
        dto.setName("Grand Hotel");
        dto.setBrand("Hilton");
        // Предположим, в DTO тоже есть структура адреса

        // When
        HotelBrief saved = hotelService.createHotel(dto);

        // Then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Grand Hotel");
    }

    @Test
    void shouldHandleElementCollection() {
        // Given
        HotelCreate dto = new HotelCreate();
        dto.setName("Resort");
        HotelBrief saved = hotelService.createHotel(dto);

        // When: тестируем метод сервиса, который работает с List<String>
        List<String> amenities = List.of("WiFi", "Pool", "Gym");
        hotelService.addAmenitiesToHotel(saved.getId(), amenities);

        // Then
        var detailed = hotelService.getHotelById(saved.getId());
        assertThat(detailed.getAmenities())
                .hasSize(3)
                .containsExactlyInAnyOrder("WiFi", "Pool", "Gym");
    }

    @Test
    void shouldWorkWithEmbeddedAddress() {
        // Проверка корректности маппинга и поиска по спецификации
        // Создаем через репозиторий для теста поиска
        HotelEntity entity = new HotelEntity();
        entity.setName("City Center");
        AddressEntity addr = new AddressEntity();
        addr.setCity("Minsk");
        entity.setAddress(addr);
        hotelRepository.save(entity);
        // Когда ищем через сервис (searchHotels использует спецификацию)
        var results = hotelService.searchHotels(null, null, "Minsk", null, null);

        assertThat(results).extracting("name").contains("City Center");
    }
    @TestConfiguration
    static class TestConfig {
        @Bean
        public BeanPostProcessor schemaInitializer(DataSource dataSource) {
            return new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) {
                    if (bean instanceof SpringLiquibase) {
                        try (var conn = dataSource.getConnection();
                             var statement = conn.createStatement()) {
                            statement.execute("CREATE SCHEMA IF NOT EXISTS HOTELS");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return bean;
                }
            };
        }
    }

}

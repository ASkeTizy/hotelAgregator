package o.e.app.service;

import liquibase.integration.spring.SpringLiquibase;
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

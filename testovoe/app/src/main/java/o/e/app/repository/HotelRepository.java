package o.e.app.repository;

import o.e.app.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<HotelEntity, Long>, JpaSpecificationExecutor<HotelEntity> {
    @Query("SELECT h.brand, COUNT(h) FROM HotelEntity h GROUP BY h.brand")
    List<Object[]> countByBrand();

    @Query("SELECT h.address.city, COUNT(h) FROM HotelEntity h GROUP BY h.address.city")
    List<Object[]> countByCity();

    @Query("SELECT h.address.country, COUNT(h) FROM HotelEntity h GROUP BY h.address.country")

    List<Object[]> countByCountry();
    @Query("SELECT a, COUNT(h) FROM HotelEntity h JOIN h.amenities a GROUP BY a")
    List<Object[]> countByAmenities();
}

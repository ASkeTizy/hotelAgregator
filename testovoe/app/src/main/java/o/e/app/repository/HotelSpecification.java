package o.e.app.repository;

import jakarta.persistence.criteria.Predicate;
import o.e.app.entity.HotelEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification  {

    public static Specification<HotelEntity> searchHotels(String name, String brand, String city, String country, String amenities) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (brand != null && !brand.isEmpty()) {
                predicates.add(cb.equal(root.get("brand"), brand));
            }

            if (city != null && !city.isEmpty()) {
                predicates.add(cb.equal(root.get("address").get("city"), city));
            }

            if (country != null && !country.isEmpty()) {
                predicates.add(cb.equal(root.get("address").get("country"), country));
            }

            if (amenities != null && !amenities.isEmpty()) {
                predicates.add(cb.isTrue(root.join("amenities").in(amenities)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

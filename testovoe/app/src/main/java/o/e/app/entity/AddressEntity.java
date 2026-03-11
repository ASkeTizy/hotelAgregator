package o.e.app.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class AddressEntity {
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
package o.e.app.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ArrivalTimeEntity {
    private String checkIn;
    private String checkOut;
}
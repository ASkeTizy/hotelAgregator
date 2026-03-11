package o.e.app.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactsEntity {
    private String phone;
    private String email;
}
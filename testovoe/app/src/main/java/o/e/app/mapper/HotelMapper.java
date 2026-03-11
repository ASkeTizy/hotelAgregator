package o.e.app.mapper;

import o.e.api.model.*;
import o.e.app.entity.AddressEntity;
import o.e.app.entity.ArrivalTimeEntity;
import o.e.app.entity.ContactsEntity;
import o.e.app.entity.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    // Mapping from entities to API models
    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts", target = "contacts")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    HotelDetailed toHotelDetailed(HotelEntity hotel);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts.phone", target = "phone")
    HotelBrief toHotelBrief(HotelEntity hotel);

    // Mapping from API models to entities
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts", target = "contacts")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    HotelEntity toHotelEntity(HotelCreate hotelCreate);

    // Update existing entity from create model (for POST /hotels)
    @Mapping(target = "id", ignore = true)
    void updateHotelEntityFromCreate(HotelCreate hotelCreate, @MappingTarget HotelEntity hotelEntity);

    // Custom implementation for updating amenities
    default void updateAmenities(List<String> amenities, @MappingTarget HotelEntity hotelEntity) {
        if (hotelEntity != null && amenities != null) {
            if (hotelEntity.getAmenities() == null) {
                hotelEntity.setAmenities(amenities);
            } else {
                hotelEntity.getAmenities().addAll(amenities);
            }
        }
    }

    // Custom mapping methods for address conversion
    default String mapAddressToString(AddressEntity address) {
        if (address == null) {
            return null;
        }
        return String.format("%s %s, %s, %s, %s",
            address.getHouseNumber(),
            address.getStreet(),
            address.getCity(),
            address.getCountry(),
            address.getPostCode());
    }

    default AddressEntity mapStringToAddress(String address) {
        if (address == null) {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        // This is a simplified parsing - you might want to implement proper parsing
        // based on your actual address format
        String[] parts = address.split(",");
        if (parts.length > 0) {
            String[] houseAndStreet = parts[0].split(" ");
            if (houseAndStreet.length > 1) {
                addressEntity.setHouseNumber(Integer.parseInt(houseAndStreet[0]));
                addressEntity.setStreet(String.join(" ", java.util.Arrays.copyOfRange(houseAndStreet, 1, houseAndStreet.length)));
            }
        }
        if (parts.length > 1) addressEntity.setCity(parts[1].trim());
        if (parts.length > 2) addressEntity.setCountry(parts[2].trim());
        if (parts.length > 3) addressEntity.setPostCode(parts[3].trim());
        return addressEntity;
    }

    // Custom mapping methods for contacts
    default Contacts mapContactsEntityToContacts(ContactsEntity contacts) {
        if (contacts == null) {
            return null;
        }
        Contacts apiContacts = new Contacts();
        apiContacts.setPhone(contacts.getPhone());
        apiContacts.setEmail(contacts.getEmail());
        return apiContacts;
    }

    default ContactsEntity mapContactsToContactsEntity(Contacts contacts) {
        if (contacts == null) {
            return null;
        }
        ContactsEntity entityContacts = new ContactsEntity();
        entityContacts.setPhone(contacts.getPhone());
        entityContacts.setEmail(contacts.getEmail());
        return entityContacts;
    }

    // Custom mapping methods for arrival time
    default ArrivalTime mapArrivalTimeEntityToArrivalTime(ArrivalTimeEntity arrivalTime) {
        if (arrivalTime == null) {
            return null;
        }
        ArrivalTime apiArrivalTime = new ArrivalTime();
        apiArrivalTime.setCheckIn(arrivalTime.getCheckIn());
        apiArrivalTime.setCheckOut(arrivalTime.getCheckOut());
        return apiArrivalTime;
    }

    default ArrivalTimeEntity mapArrivalTimeToArrivalTimeEntity(ArrivalTime arrivalTime) {
        if (arrivalTime == null) {
            return null;
        }
        ArrivalTimeEntity entityArrivalTime = new ArrivalTimeEntity();
        entityArrivalTime.setCheckIn(arrivalTime.getCheckIn());
        entityArrivalTime.setCheckOut(arrivalTime.getCheckOut());
        return entityArrivalTime;
    }
}

package o.e.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import o.e.api.model.Address;
import o.e.api.model.ArrivalTime;
import o.e.api.model.Contacts;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * HotelDetailed
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-09T20:58:53.400428235+03:00[Europe/Moscow]", comments = "Generator version: 7.20.0")
public class HotelDetailed {

  private @Nullable Long id;

  private @Nullable String name;

  private @Nullable String description;

  private @Nullable String brand;

  private @Nullable Address address;

  private @Nullable Contacts contacts;

  private @Nullable ArrivalTime arrivalTime;

  @Valid
  private List<String> amenities = new ArrayList<>();

  public HotelDetailed id(@Nullable Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable Long getId() {
    return id;
  }

  public void setId(@Nullable Long id) {
    this.id = id;
  }

  public HotelDetailed name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "DoubleTree by Hilton Minsk", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public HotelDetailed description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public HotelDetailed brand(@Nullable String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Get brand
   * @return brand
   */
  
  @Schema(name = "brand", example = "Hilton", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("brand")
  public @Nullable String getBrand() {
    return brand;
  }

  public void setBrand(@Nullable String brand) {
    this.brand = brand;
  }

  public HotelDetailed address(@Nullable Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   */
  @Valid 
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public @Nullable Address getAddress() {
    return address;
  }

  public void setAddress(@Nullable Address address) {
    this.address = address;
  }

  public HotelDetailed contacts(@Nullable Contacts contacts) {
    this.contacts = contacts;
    return this;
  }

  /**
   * Get contacts
   * @return contacts
   */
  @Valid 
  @Schema(name = "contacts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contacts")
  public @Nullable Contacts getContacts() {
    return contacts;
  }

  public void setContacts(@Nullable Contacts contacts) {
    this.contacts = contacts;
  }

  public HotelDetailed arrivalTime(@Nullable ArrivalTime arrivalTime) {
    this.arrivalTime = arrivalTime;
    return this;
  }

  /**
   * Get arrivalTime
   * @return arrivalTime
   */
  @Valid 
  @Schema(name = "arrivalTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("arrivalTime")
  public @Nullable ArrivalTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(@Nullable ArrivalTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public HotelDetailed amenities(List<String> amenities) {
    this.amenities = amenities;
    return this;
  }

  public HotelDetailed addAmenitiesItem(String amenitiesItem) {
    if (this.amenities == null) {
      this.amenities = new ArrayList<>();
    }
    this.amenities.add(amenitiesItem);
    return this;
  }

  /**
   * Get amenities
   * @return amenities
   */
  
  @Schema(name = "amenities", example = "[\"Free parking\",\"Free WiFi\",\"Non-smoking rooms\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amenities")
  public List<String> getAmenities() {
    return amenities;
  }

  public void setAmenities(List<String> amenities) {
    this.amenities = amenities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HotelDetailed hotelDetailed = (HotelDetailed) o;
    return Objects.equals(this.id, hotelDetailed.id) &&
        Objects.equals(this.name, hotelDetailed.name) &&
        Objects.equals(this.description, hotelDetailed.description) &&
        Objects.equals(this.brand, hotelDetailed.brand) &&
        Objects.equals(this.address, hotelDetailed.address) &&
        Objects.equals(this.contacts, hotelDetailed.contacts) &&
        Objects.equals(this.arrivalTime, hotelDetailed.arrivalTime) &&
        Objects.equals(this.amenities, hotelDetailed.amenities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, brand, address, contacts, arrivalTime, amenities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HotelDetailed {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contacts: ").append(toIndentedString(contacts)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
    sb.append("    amenities: ").append(toIndentedString(amenities)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


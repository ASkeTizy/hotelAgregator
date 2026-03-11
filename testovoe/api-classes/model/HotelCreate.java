package o.e.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * HotelCreate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-09T20:58:53.400428235+03:00[Europe/Moscow]", comments = "Generator version: 7.20.0")
public class HotelCreate {

  private @Nullable String name;

  private @Nullable String description;

  private @Nullable String brand;

  private @Nullable Address address;

  private @Nullable Contacts contacts;

  private @Nullable ArrivalTime arrivalTime;

  public HotelCreate name(@Nullable String name) {
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

  public HotelCreate description(@Nullable String description) {
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

  public HotelCreate brand(@Nullable String brand) {
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

  public HotelCreate address(@Nullable Address address) {
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

  public HotelCreate contacts(@Nullable Contacts contacts) {
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

  public HotelCreate arrivalTime(@Nullable ArrivalTime arrivalTime) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HotelCreate hotelCreate = (HotelCreate) o;
    return Objects.equals(this.name, hotelCreate.name) &&
        Objects.equals(this.description, hotelCreate.description) &&
        Objects.equals(this.brand, hotelCreate.brand) &&
        Objects.equals(this.address, hotelCreate.address) &&
        Objects.equals(this.contacts, hotelCreate.contacts) &&
        Objects.equals(this.arrivalTime, hotelCreate.arrivalTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, brand, address, contacts, arrivalTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HotelCreate {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contacts: ").append(toIndentedString(contacts)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
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


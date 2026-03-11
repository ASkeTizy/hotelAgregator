package o.e.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ArrivalTime
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-09T20:58:53.400428235+03:00[Europe/Moscow]", comments = "Generator version: 7.20.0")
public class ArrivalTime {

  private @Nullable String checkIn;

  private @Nullable String checkOut;

  public ArrivalTime checkIn(@Nullable String checkIn) {
    this.checkIn = checkIn;
    return this;
  }

  /**
   * Get checkIn
   * @return checkIn
   */
  
  @Schema(name = "checkIn", example = "14:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("checkIn")
  public @Nullable String getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(@Nullable String checkIn) {
    this.checkIn = checkIn;
  }

  public ArrivalTime checkOut(@Nullable String checkOut) {
    this.checkOut = checkOut;
    return this;
  }

  /**
   * Get checkOut
   * @return checkOut
   */
  
  @Schema(name = "checkOut", example = "12:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("checkOut")
  public @Nullable String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(@Nullable String checkOut) {
    this.checkOut = checkOut;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrivalTime arrivalTime = (ArrivalTime) o;
    return Objects.equals(this.checkIn, arrivalTime.checkIn) &&
        Objects.equals(this.checkOut, arrivalTime.checkOut);
  }

  @Override
  public int hashCode() {
    return Objects.hash(checkIn, checkOut);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArrivalTime {\n");
    sb.append("    checkIn: ").append(toIndentedString(checkIn)).append("\n");
    sb.append("    checkOut: ").append(toIndentedString(checkOut)).append("\n");
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


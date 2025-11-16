package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = KrakenTickerEntryDeserializer.class)
public class KrakenTickerEntry {
  private int timestamp;
  private BigDecimal open;
  private Double high;
  private Double low;
  private BigDecimal close;
  private Double volume;
  private Double quoteVolume;
  private int count;

  @JsonIgnore
  public BigDecimal getAvarage() {
    return BigDecimal.valueOf((this.high + this.low) / 2);
  }

  @JsonIgnore
  public ZonedDateTime getDateTime() {
    Instant instant = Instant.ofEpochSecond(this.timestamp);
    return instant.atZone(ZoneId.of("UTC"));
  }
}

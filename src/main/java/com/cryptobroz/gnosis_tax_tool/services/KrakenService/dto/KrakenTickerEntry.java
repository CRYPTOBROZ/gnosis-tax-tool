package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

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
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Double volume;
  private Double quoteVolume;
  private int count;

  @JsonIgnore
  public double getAvarage() {
    return (open + close) / 2;
  }

  @JsonIgnore
  public ZonedDateTime getDateTime() {
    Instant instant = Instant.ofEpochSecond(this.timestamp);
    return instant.atZone(ZoneId.of("UTC"));
  }
}

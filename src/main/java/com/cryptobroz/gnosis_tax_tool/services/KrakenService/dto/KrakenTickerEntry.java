package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = KrakenTickerEntryDeserializer.class)
public record KrakenTickerEntry(int timestamp, BigDecimal open, Double high, Double low, BigDecimal close,
    Double volume, Double quoteVolume, int count) {

  @JsonIgnore
  public ZonedDateTime getZonedDateTime() {
    Instant instant = Instant.ofEpochSecond(this.timestamp);
    return instant.atZone(ZoneId.of("UTC"));
  }
}

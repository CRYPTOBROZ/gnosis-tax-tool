package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = TickerEntryDeserializer.class)
public class TickerEntry {
  private long timestamp;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Double volume;
  private Double quoteVolume;
  private int count;

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public Double getOpen() {
    return open;
  }

  public void setOpen(Double open) {
    this.open = open;
  }

  public Double getHigh() {
    return high;
  }

  public void setHigh(Double high) {
    this.high = high;
  }

  public Double getLow() {
    return low;
  }

  public void setLow(Double low) {
    this.low = low;
  }

  public Double getClose() {
    return close;
  }

  public void setClose(Double close) {
    this.close = close;
  }

  public Double getVolume() {
    return volume;
  }

  public void setVolume(Double volume) {
    this.volume = volume;
  }

  public Double getQuoteVolume() {
    return quoteVolume;
  }

  public void setQuoteVolume(Double quoteVolume) {
    this.quoteVolume = quoteVolume;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "TickerEntry{" +
        "timestamp=" + timestamp +
        ", open='" + open + '\'' +
        ", high='" + high + '\'' +
        ", low='" + low + '\'' +
        ", close='" + close + '\'' +
        ", volume='" + volume + '\'' +
        ", quoteVolume='" + quoteVolume + '\'' +
        ", count=" + count +
        '}';
  }
}

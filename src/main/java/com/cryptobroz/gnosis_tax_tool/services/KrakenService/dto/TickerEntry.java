package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = TickerEntryDeserializer.class)
public class TickerEntry {
  private long timestamp;
  private String open;
  private String high;
  private String low;
  private String close;
  private String volume;
  private String quoteVolume;
  private int count;

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLow() {
    return low;
  }

  public void setLow(String low) {
    this.low = low;
  }

  public String getClose() {
    return close;
  }

  public void setClose(String close) {
    this.close = close;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getQuoteVolume() {
    return quoteVolume;
  }

  public void setQuoteVolume(String quoteVolume) {
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

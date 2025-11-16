package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KrakenResponseResult {
  private final ObjectMapper objectMapper = new ObjectMapper();

  private Map<String, List<KrakenTickerEntry>> pairs = new HashMap<>();
  private long last;

  public long getLast() {
    return last;
  }

  public void setLast(long last) {
    this.last = last;
  }

  @JsonAnySetter
  public void addPair(String key, Object value) {
    if ("last".equals(key)) {
      // handled separately
      return;
    }
    if (value instanceof List) {
      List<KrakenTickerEntry> tickerEntries = objectMapper.convertValue(value,
          new TypeReference<List<KrakenTickerEntry>>() {
          });
      if (tickerEntries == null) {
        return;
      }
      pairs.put(key, tickerEntries);
    }
  }

  public Map<String, List<KrakenTickerEntry>> getPairs() {
    return pairs;
  }
}
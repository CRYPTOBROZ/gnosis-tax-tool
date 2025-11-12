package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class KrakenResponseResult {
  private long last;
  private Map<String, List<List<Object>>> pairs = new HashMap<>();

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
      pairs.put(key, (List<List<Object>>) value);
    }
  }

  public Map<String, List<List<Object>>> getPairs() {
    return pairs;
  }

  public void setPairs(Map<String, List<List<Object>>> pairs) {
    this.pairs = pairs;
  }
}
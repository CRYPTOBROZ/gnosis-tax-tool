package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.util.List;

public class KrakenResponse {
  private List<String> error;
  private KrakenResponseResult result;

  public List<String> getError() {
    return error;
  }

  public void setError(List<String> error) {
    this.error = error;
  }

  public KrakenResponseResult getResult() {
    return result;
  }

  public void setResult(KrakenResponseResult result) {
    this.result = result;
  }
}
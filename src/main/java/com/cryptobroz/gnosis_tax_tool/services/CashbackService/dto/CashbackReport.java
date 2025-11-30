package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.util.List;

public record CashbackReport(List<Cashback> cashbacks) {
  public BigDecimal getEurCashbackTotal() {
    return cashbacks.stream()
        .map(Cashback::getEurPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getGnoCashbackTotal() {
    return cashbacks.stream()
        .map(Cashback::getGnoPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
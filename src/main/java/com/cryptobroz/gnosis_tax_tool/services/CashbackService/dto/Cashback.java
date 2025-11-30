package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

public record Cashback(ZonedDateTime dateTime, String hash, String to, String from, long value, int decimal,
    ZonedDateTime zonedDateTime, BigDecimal open, BigDecimal close, BigDecimal avarage) {

  public static Cashback fromTransactionAndDatePrice(EtherScanTransaction transaction, DatePrice datePrice) {
    return new Cashback(
        transaction.getZonedDateTime(), transaction.hash(), transaction.to(), transaction.from(), transaction.value(),
        transaction.tokenDecimal(), datePrice.zonedDateTime(), datePrice.open(), datePrice.close(),
        datePrice.avarage());
  }

  public BigDecimal getGnoPrice() {
    return BigDecimal.valueOf(value).movePointLeft(decimal);
  }

  public BigDecimal getEurPrice() {
    return getGnoPrice().multiply(avarage);
  }
}

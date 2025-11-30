package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

public record Cashback(ZonedDateTime transactionZonedDateTime, String hash, String to, String from, long value,
    int decimal, BigDecimal open, BigDecimal close) {

  public static Cashback fromTransactionAndDatePrice(EtherScanTransaction transaction, DatePrice datePrice) {
    return new Cashback(
        transaction.getZonedDateTime(), transaction.hash(), transaction.to(), transaction.from(), transaction.value(),
        transaction.tokenDecimal(), datePrice.open(), datePrice.close());
  }

  public BigDecimal getGnoPrice() {
    return BigDecimal.valueOf(value).movePointLeft(decimal);
  }

  public BigDecimal getEurPrice() {
    return getGnoPrice().multiply(this.getAvarageEurPrice());
  }

  public ZonedDateTime getHelsinkiZonedDateTime() {
    return transactionZonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Helsinki"));
  }

  public String getShortenedHash() {
    return shortenHash(hash, 15);
  }

  public BigDecimal getAvarageEurPrice() {
    BigDecimal sum = open.add(close);
    return sum.divide(BigDecimal.valueOf(2));
  }

  public static String shortenHash(String hash, int visibleChars) {
    if (hash == null || hash.length() <= 2 * visibleChars) {
      return hash; // too short to shorten
    }
    return hash.substring(0, visibleChars) + "..." + hash.substring(hash.length() - visibleChars);
  }
}

package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

import lombok.Data;

@Data
public class Cashback {
  private ZonedDateTime dateTime;

  private String hash;

  private String to;
  private String from;

  private long value;
  private int decimal;

  private ZonedDateTime priceDateTime;

  private BigDecimal open;
  private BigDecimal close;
  private BigDecimal avarage;

  public static Cashback fromTransaction(EtherScanTransaction transaction) {
    Cashback cashback = new Cashback();
    cashback.setDateTime(transaction.getDateTime());
    cashback.setHash(transaction.getHash());
    cashback.setTo(transaction.getTo());
    cashback.setFrom(transaction.getFrom());
    cashback.setValue(transaction.getValue());
    cashback.setDecimal(transaction.getTokenDecimal());
    return cashback;
  }

  public Cashback copyDatePrice(DatePrice datePrice) {
    this.setOpen(datePrice.getOpen());
    this.setClose(datePrice.getClose());
    this.setAvarage(datePrice.getAvarage());
    return this;
  }

  public BigDecimal getGnoPrice() {
    return BigDecimal.valueOf(value).movePointLeft(decimal);
  }

  public BigDecimal getEurPrice() {
    return getGnoPrice().multiply(avarage);
  }
}

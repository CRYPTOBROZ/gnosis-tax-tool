package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;

import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenTickerEntry;

import lombok.Data;

@Data
public class DatePrice {
  private BigDecimal open;
  private BigDecimal close;
  private BigDecimal avarage;

  public static DatePrice fromKrakenTickerEntry(final KrakenTickerEntry tickerEntry) {
    DatePrice datePrice = new DatePrice();
    datePrice.setOpen(tickerEntry.getOpen());
    datePrice.setClose(tickerEntry.getClose());
    datePrice.setAvarage(tickerEntry.getAvarage());
    return datePrice;
  }
}

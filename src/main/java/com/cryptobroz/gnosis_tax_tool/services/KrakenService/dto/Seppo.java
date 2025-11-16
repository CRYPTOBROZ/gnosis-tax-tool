package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import lombok.Data;

@Data
public class Seppo {
  private Double open;
  private Double close;
  private Double avarage;

  public static Seppo fromKrakenTickerEntry(final KrakenTickerEntry tickerEntry) {
    Seppo seppo = new Seppo();
    seppo.setOpen(tickerEntry.getOpen());
    seppo.setClose(tickerEntry.getClose());
    seppo.setAvarage(tickerEntry.getAvarage());
    return seppo;
  }
}

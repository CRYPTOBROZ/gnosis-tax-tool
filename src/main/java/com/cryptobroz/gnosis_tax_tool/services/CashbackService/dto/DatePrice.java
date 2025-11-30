package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenTickerEntry;

public record DatePrice(ZonedDateTime zonedDateTime, BigDecimal open, BigDecimal close, BigDecimal avarage) {
  public static DatePrice fromKrakenTickerEntry(final ZonedDateTime zonedDateTime,
      final KrakenTickerEntry tickerEntry) {
    return new DatePrice(zonedDateTime, tickerEntry.open(), tickerEntry.close(), tickerEntry.getAvarage());
  }
}

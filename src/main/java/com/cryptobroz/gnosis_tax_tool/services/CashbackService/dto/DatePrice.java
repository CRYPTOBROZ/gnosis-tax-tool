package com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenTicker;

public record DatePrice(ZonedDateTime zonedDateTime, BigDecimal open, BigDecimal close) {
  public static DatePrice fromKrakenTickerEntry(final ZonedDateTime zonedDateTime,
      final KrakenTicker tickerEntry) {
    return new DatePrice(zonedDateTime, tickerEntry.open(), tickerEntry.close());
  }
}

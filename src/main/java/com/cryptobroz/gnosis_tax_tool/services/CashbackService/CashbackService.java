package com.cryptobroz.gnosis_tax_tool.services.CashbackService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.Cashback;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.DatePrice;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.CashbackReport;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.KrakenService;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenTickerEntry;

@Service
public class CashbackService {
  EtherScanService etherScanService;
  KrakenService krakenService;

  public CashbackService(EtherScanService etherScanService, KrakenService krakenService) {
    this.etherScanService = etherScanService;
    this.krakenService = krakenService;
  }

  public Map<ZonedDateTime, DatePrice> getDatePrices() {
    int currentYear = java.time.Year.now().getValue();
    List<KrakenTickerEntry> tickers = krakenService.fetchOHLC(currentYear)
        .result()
        .getPairs()
        .get(KrakenService.GNO_EUR_PAIR);

    return tickers.stream()
        .collect(Collectors.toMap(
            e -> e.getZonedDateTime(),
            (tickerEntry) -> {
              return DatePrice.fromKrakenTickerEntry(tickerEntry.getZonedDateTime(), tickerEntry);
            },
            (a, b) -> a,
            TreeMap::new));
  }

  public List<Cashback> getCashbacks() {
    Map<ZonedDateTime, DatePrice> datePrices = getDatePrices();
    List<EtherScanTransaction> transactions = etherScanService.fetchCurrentYearCashbackTransactions();

    return transactions.stream().map(transaction -> {
      ZonedDateTime dateTime = transaction.getZonedDateTime().toLocalDate()
          .atStartOfDay(transaction.getZonedDateTime().getZone());
      DatePrice datePrice = datePrices.get(dateTime);
      return Cashback.fromTransactionAndDatePrice(transaction, datePrice);
    }).toList();
  }

  public CashbackReport getCashbackReport() {
    List<Cashback> cashbacks = this.getCashbacks();
    return new CashbackReport(cashbacks);
  }
}

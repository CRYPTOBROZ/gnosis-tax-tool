package com.cryptobroz.gnosis_tax_tool.services.CashbackService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.Cashback;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.DatePrice;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.KrakenService;

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
    return krakenService.fetchOHLC(currentYear)
        .getResult()
        .getPairs()
        .get(KrakenService.GNO_EUR_PAIR)
        .stream()
        .collect(Collectors.toMap(
            e -> e.getDateTime(),
            DatePrice::fromKrakenTickerEntry,
            (a, b) -> a,
            TreeMap::new));
  }

  public List<Cashback> getCashbacks() {
    Map<ZonedDateTime, DatePrice> datePrices = getDatePrices();
    List<EtherScanTransaction> transactions = etherScanService.fetchCurrentYearCashbackTransactions();

    return transactions.stream().map(transaction -> {
      ZonedDateTime dateOnly = transaction.getDateTime().toLocalDate()
          .atStartOfDay(transaction.getDateTime().getZone());
      DatePrice price = datePrices.get(dateOnly);

      Cashback cashback = Cashback.fromTransaction(transaction);
      cashback.copyDatePrice(price);
      cashback.setPriceDateTime(dateOnly);

      return cashback;
    }).toList();

  }
}

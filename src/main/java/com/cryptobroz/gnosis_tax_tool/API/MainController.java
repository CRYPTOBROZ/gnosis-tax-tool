package com.cryptobroz.gnosis_tax_tool.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptobroz.gnosis_tax_tool.services.CashbackService.CashbackService;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.Cashback;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.KrakenService;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenResponse;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenTickerEntry;

@RestController
public class MainController {

  EtherScanService etherScanService;
  KrakenService krakenService;
  CashbackService cashbackService;

  public MainController(EtherScanService etherScanService, KrakenService krakenService,
      CashbackService cashbackService) {
    this.etherScanService = etherScanService;
    this.krakenService = krakenService;
    this.cashbackService = cashbackService;
  }

  @GetMapping("/")
  public String main() {
    return "CRYPTOBROZ Gnosis Tax Tool API is running!";
  }

  @GetMapping("/api/v1/ohlc")
  public KrakenResponse ohlc() {
    int currentYear = java.time.Year.now().getValue();
    KrakenResponse response = krakenService.fetchOHLC(currentYear);
    return response;
  }

  @GetMapping("/api/v1/openclose")
  public Object openCloseDate() {
    return cashbackService.getDatePrices();
  }

  @GetMapping("/api/v1/cashbacks")
  public List<Cashback> cashbackPrices() {
    return cashbackService.getCashbackPrices();
  }

  @GetMapping("/api/v1/cashback/transactions")
  public List<EtherScanTransaction> currentYearCashbackTransactions() {
    return etherScanService.fetchCurrentYearCashbackTransactions();
  }
}
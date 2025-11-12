package com.cryptobroz.gnosis_tax_tool.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.KrakenService;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenResponse;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.TickerEntry;

@RestController
public class MainController {

  EtherScanService etherScanService;
  KrakenService krakenService;

  public MainController(EtherScanService etherScanService, KrakenService krakenService) {
    this.etherScanService = etherScanService;
    this.krakenService = krakenService;
  }

  @GetMapping("/")
  public String main() {
    return "CRYPTOBROZ Gnosis Tax Tool API is running!";
  }

  @GetMapping("/api/v1/cashback")
  public List<EtherScanTransaction> currentYearCashbackTransactions() {
    return etherScanService.fetchCurrentYearCashbackTransactions();
  }

  @GetMapping("/api/v1/ohlc")
  public KrakenResponse krakenohlc() {
    int currentYear = java.time.Year.now().getValue();
    KrakenResponse response = krakenService.fetchOHLC(currentYear);
    return response;
  }
}
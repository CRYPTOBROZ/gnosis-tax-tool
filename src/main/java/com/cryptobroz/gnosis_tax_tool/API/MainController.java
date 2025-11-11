package com.cryptobroz.gnosis_tax_tool.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptobroz.gnosis_tax_tool.services.CoinGeckoService.CoingGeckoService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

@RestController
public class MainController {

  EtherScanService etherScanService;
  CoingGeckoService coingGeckoService;

  public MainController(@Autowired EtherScanService etherScanService, @Autowired CoingGeckoService coingGeckoService) {
    this.etherScanService = etherScanService;
    this.coingGeckoService = coingGeckoService;
  }

  @GetMapping("/")
  public String main() {
    return "CRYPTOBROZ Gnosis Tax Tool API is running!";
  }

  @GetMapping("/api/v1/cashback")
  public List<EtherScanTransaction> currentYearCashbackTransactions() {
    return etherScanService.fetchCurrentYearCashbackTransactions();
  }

  @GetMapping("/api/v1/asd")
  public List<EtherScanTransaction> wowsuchendpoint() {
    coingGeckoService.fetchPrice();
    return null;
  }
}
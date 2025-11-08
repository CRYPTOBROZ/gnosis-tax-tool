package com.cryptobroz.gnosis_tax_tool.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.EtherScanService;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

@RestController
public class MainController {

  EtherScanService etherScanService;

  public MainController(@Autowired EtherScanService etherScanService) {
    this.etherScanService = etherScanService;
  }

  @GetMapping("/")
  public String main() {
    return "CRYPTOBROZ Gnosis Tax Tool API is running!";
  }

  @GetMapping("/api/v1/transactions")
  public List<EtherScanTransaction> test() {
    return etherScanService.fetchTransactions();
  }
}
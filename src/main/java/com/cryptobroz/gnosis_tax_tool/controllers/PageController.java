package com.cryptobroz.gnosis_tax_tool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cryptobroz.gnosis_tax_tool.services.CashbackService.CashbackService;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.CashbackReport;

@Controller
public class PageController {

  CashbackService cashbackService;

  public PageController(CashbackService cashbackService) {
    this.cashbackService = cashbackService;
  }

  @GetMapping("/")
  public String index(Model model) {
    CashbackReport cashbackReport = cashbackService.getCashbackReport();
    model.addAttribute("report", cashbackReport);
    return "index";
  }
}

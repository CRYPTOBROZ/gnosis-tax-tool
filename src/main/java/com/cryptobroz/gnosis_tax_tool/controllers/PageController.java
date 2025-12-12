package com.cryptobroz.gnosis_tax_tool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cryptobroz.gnosis_tax_tool.enums.SelectedYear;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.CashbackService;
import com.cryptobroz.gnosis_tax_tool.services.CashbackService.dto.CashbackReport;

@Controller
public class PageController {
  private final CashbackService cashbackService;

  public PageController(CashbackService cashbackService) {
    this.cashbackService = cashbackService;
  }

  @GetMapping("/")
  public String index(Model model,
      @RequestParam(required = false, defaultValue = "CURRENT") SelectedYear selectedYear) {
    CashbackReport cashbackReport = cashbackService.getCashbackReport(selectedYear.toInt());
    model.addAttribute("report", cashbackReport);
    model.addAttribute("selectedYear", selectedYear);
    return "index";
  }
}

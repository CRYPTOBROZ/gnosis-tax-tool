package com.cryptobroz.gnosis_tax_tool.API;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping("/")
  public String index() {
    return "index";
  }
}

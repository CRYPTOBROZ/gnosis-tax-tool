package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto;

import java.util.List;

import lombok.Data;

@Data
public class EtherScanResponse {
  private String status;
  private String message;
  private List<EtherScanTransaction> result;
}
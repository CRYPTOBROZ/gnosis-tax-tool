package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto;

import lombok.Data;

@Data
public class EtherScanTransaction {
  private String blockNumber;
  private String timeStamp;
  private String hash;
  private String nonce;
  private String blockHash;
  private String from;
  private String contractAddress;
  private String to;
  private String value;
  private String tokenName;
  private String tokenSymbol;
  private String tokenDecimal;
  private String transactionIndex;
  private String gas;
  private String gasPrice;
  private String gasUsed;
  private String cumulativeGasUsed;
  private String input;
  private String methodId;
  private String functionName;
  private String confirmations;
}
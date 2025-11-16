package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class EtherScanTransaction {
  private long blockNumber;
  private long timeStamp;
  private String hash;
  private String nonce;
  private String blockHash;
  private String from;
  private String contractAddress;
  private String to;
  private long value;
  private String tokenName;
  private String tokenSymbol;
  private int tokenDecimal;
  private String transactionIndex;
  private long gas;
  private long gasPrice;
  private long gasUsed;
  private long cumulativeGasUsed;
  private String input;
  private String methodId;
  private String functionName;
  private String confirmations;

  @JsonIgnore
  public ZonedDateTime getDateTime() {
    Instant instant = Instant.ofEpochSecond(this.timeStamp);
    return instant.atZone(ZoneId.of("UTC"));
  }
}
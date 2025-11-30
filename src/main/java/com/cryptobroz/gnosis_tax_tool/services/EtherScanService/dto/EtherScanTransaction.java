package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record EtherScanTransaction(
    long blockNumber,
    long timeStamp,
    String hash,
    String nonce,
    String blockHash,
    String from,
    String contractAddress,
    String to,
    long value,
    String tokenName,
    String tokenSymbol,
    int tokenDecimal,
    String transactionIndex,
    long gas,
    long gasPrice,
    long gasUsed,
    long cumulativeGasUsed,
    String input,
    String methodId,
    String functionName,
    String confirmations) {

  @JsonIgnore
  public ZonedDateTime getZonedDateTime() {
    Instant instant = Instant.ofEpochSecond(this.timeStamp);
    return instant.atZone(ZoneId.of("UTC"));
  }
}
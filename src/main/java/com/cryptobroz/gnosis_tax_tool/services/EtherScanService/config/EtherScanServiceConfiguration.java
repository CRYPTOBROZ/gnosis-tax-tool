package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtherScanServiceConfiguration {
  @Value("${etherscan.api.key}")
  private String apiKey;

  @Value("${gnosis.wallet.address}")
  private String gnosisWalletAddress;

  public String getApiKey() {
    return apiKey;
  }

  public String getGnosisWalletAddress() {
    return gnosisWalletAddress;
  }
}
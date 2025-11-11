package com.cryptobroz.gnosis_tax_tool.services.CoinGeckoService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinGeckoConfiguration {
  @Value("${coing.gecko.api.key}")
  private String apiKey;

  @Value("${gnosis.wallet.address}")
  private String gnosisWalletAddress;

  public String getApiKey() {
    return apiKey;
  }

  public String getGnosisWalletAddress() {
    return getGnosisWalletAddress();
  }
}

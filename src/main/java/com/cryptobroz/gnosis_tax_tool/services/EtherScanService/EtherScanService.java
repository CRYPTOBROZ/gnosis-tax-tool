package com.cryptobroz.gnosis_tax_tool.services.EtherScanService;

import java.net.URI;
import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.config.EtherScanServiceConfiguration;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanResponse;
import com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto.EtherScanTransaction;

@Service
public class EtherScanService {
  private final String BASE_URL = "https://api.etherscan.io/v2/api";
  private final String GNOSIS_CONTRACT_ADDRESS = "0x9C58BAcC331c9aa871AFD802DB6379a98e80CEdb";
  private final int GNOSIS_CHAIN_ID = 100;

  // This is transfer methodId?
  // seems to be related to cashbacks
  // TODO: Is this documented somewhere?
  private final String METHOD_ID = "0x6a761202";

  // max 100 results per page, more like limit than "offset"
  // year has 54 weeks so we expect 54 cashback transactions per year
  // so 100 transactions should be enough to cover cashbacks and few extra
  private final int OFFSET = 100;

  private final RestTemplate restTemplate;
  private final EtherScanServiceConfiguration configuration;

  public EtherScanService(EtherScanServiceConfiguration configuration) {
    this.restTemplate = new RestTemplate();
    this.configuration = configuration;
  }

  public URI buildURI() {
    final String apiKey = configuration.getApiKey();
    final String gnosisWalletAddress = configuration.getGnosisWalletAddress();

    return UriComponentsBuilder.fromUriString(BASE_URL)
        .queryParam("apikey", apiKey)
        .queryParam("chainId", GNOSIS_CHAIN_ID)
        .queryParam("module", "account")
        .queryParam("action", "tokentx")
        .queryParam("contractaddress", GNOSIS_CONTRACT_ADDRESS)
        .queryParam("address", gnosisWalletAddress)
        .queryParam("startblock", 0)
        .queryParam("endblock", 999999999)
        .queryParam("page", 1)
        .queryParam("offset", OFFSET)
        .queryParam("sort", "desc") // latest first
        .build().toUri();
  }

  // Get ERC20 Token Transfers by Address
  public List<EtherScanTransaction> fetchCurrentYearCashbackTransactions() {
    final String gnosisWalletAddress = configuration.getGnosisWalletAddress();
    final URI uri = buildURI();

    if (uri == null) {
      throw new IllegalStateException("URI is null");
    }

    EtherScanResponse response = restTemplate.getForObject(uri, EtherScanResponse.class);

    if (response == null) {
      throw new IllegalStateException("Response is null");
    }

    List<EtherScanTransaction> transactions = response.getResult();

    if (transactions == null) {
      throw new IllegalStateException("Transactions are null");
    }

    int currentYear = Year.now().getValue();

    return transactions.stream()
        .filter(tx -> {
          int txYear = tx.getDateTime().getYear();
          return txYear == currentYear;
        })
        .filter(tx -> tx.getMethodId() != null && tx.getMethodId().equals(METHOD_ID))
        .filter(tx -> tx.getTo() != null && tx.getTo().equalsIgnoreCase(gnosisWalletAddress))
        .toList();
  }
}

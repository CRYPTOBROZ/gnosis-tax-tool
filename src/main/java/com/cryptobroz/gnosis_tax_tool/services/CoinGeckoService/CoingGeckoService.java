package com.cryptobroz.gnosis_tax_tool.services.CoinGeckoService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptobroz.gnosis_tax_tool.services.CoinGeckoService.config.CoinGeckoConfiguration;

@Service
public class CoingGeckoService {

  private final CoinGeckoConfiguration configuration;
  private final RestTemplate restTemplate;

  private final String BASE_URL = "https://api.coingecko.com/api/v3/coins/gnosis/market_chart";

  public CoingGeckoService(@Autowired CoinGeckoConfiguration configuration) {
    this.restTemplate = new RestTemplate();
    this.configuration = configuration;
  }

  public URI getURI() {
    return UriComponentsBuilder.fromUriString(BASE_URL)
        .queryParam("vs_currency", "eur")
        .queryParam("days", 30)
        .queryParam("interval", "daily")
        .build().toUri();
  }

  public HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-cg-demo-api-key", configuration.getApiKey());
    return headers;
  }

  public void fetchPrice() {
    URI uri = getURI();
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);

    // String response = restTemplate.getForObject(uri, String.class, entity);

    ResponseEntity<String> response = restTemplate.exchange(
        uri,
        HttpMethod.GET,
        entity,
        String.class);

    System.out.println(response);
  }

}

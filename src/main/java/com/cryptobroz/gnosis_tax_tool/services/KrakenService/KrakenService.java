package com.cryptobroz.gnosis_tax_tool.services.KrakenService;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenResponse;

@Service
public class KrakenService {

  private final RestTemplate restTemplate;

  private final String BASE_URL = "https://api.kraken.com/0/public/OHLC";

  public KrakenService() {
    this.restTemplate = new RestTemplate();
  }

  public URI getURI() {
    return UriComponentsBuilder.fromUriString(BASE_URL)
        .queryParam("pair", "GNO/EUR")
        .queryParam("interval", 1440) // daily
        .build().toUri();
  }

  public KrakenResponse fetchOHCL() {
    URI uri = getURI();
    ResponseEntity<KrakenResponse> response = restTemplate.getForEntity(uri, KrakenResponse.class);
    return response.getBody();
  }

}

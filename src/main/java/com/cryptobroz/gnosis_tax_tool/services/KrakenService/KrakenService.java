package com.cryptobroz.gnosis_tax_tool.services.KrakenService;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenResponse;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.KrakenResponseResult;
import com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto.Seppo;

@Service
public class KrakenService {

  private final RestTemplate restTemplate;
  private final String PAIR = "GNO/EUR";
  private final int INTERVAL = 1440; // daily

  private final String BASE_URL = "https://api.kraken.com/0/public/OHLC";

  public KrakenService() {
    this.restTemplate = new RestTemplate();
  }

  public Map<ZonedDateTime, Seppo> openCloseDate() {
    int currentYear = java.time.Year.now().getValue();
    return fetchOHLC(currentYear)
        .getResult()
        .getPairs()
        .get(PAIR)
        .stream()
        .collect(Collectors.toMap(
            e -> e.getDateTime(),
            Seppo::fromKrakenTickerEntry,
            (a, b) -> a,
            TreeMap::new));
  }

  public KrakenResponse fetchOHLC(int year) {
    Instant firstDayOfYear = LocalDate.of(year, 1, 1)
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant();
    int sinceEpoch = (int) firstDayOfYear.getEpochSecond();
    return fetchOHLC(PAIR, INTERVAL, sinceEpoch);
  }

  // Returns up to 720 of the most recent entries (older data cannot be retrieved,
  // regardless of the value of since)
  public KrakenResponse fetchOHLC(final String pair, final int interval, int since) {
    URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
        .queryParam("pair", pair)
        .queryParam("interval", interval)
        .queryParam("since", since)
        .build().toUri();

    // TODO: This probably needs some exception handling
    ResponseEntity<KrakenResponse> response = restTemplate.getForEntity(uri, KrakenResponse.class);
    return response.getBody();
  }
}

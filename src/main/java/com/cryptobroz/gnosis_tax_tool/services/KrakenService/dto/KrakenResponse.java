package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.util.List;

public record KrakenResponse(List<String> error, KrakenResponseResult result) {
}
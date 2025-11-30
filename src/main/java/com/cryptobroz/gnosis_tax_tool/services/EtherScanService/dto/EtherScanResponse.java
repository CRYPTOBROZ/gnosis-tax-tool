package com.cryptobroz.gnosis_tax_tool.services.EtherScanService.dto;

import java.util.List;

public record EtherScanResponse(String status, String message, List<EtherScanTransaction> result) {
}
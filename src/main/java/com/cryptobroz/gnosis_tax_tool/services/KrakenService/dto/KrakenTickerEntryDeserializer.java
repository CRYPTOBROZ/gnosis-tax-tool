package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class KrakenTickerEntryDeserializer extends JsonDeserializer<KrakenTickerEntry> {
    @Override
    public KrakenTickerEntry deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);

        if (node.isArray() && node.size() == 8) {
            return new KrakenTickerEntry(
                    node.get(0).asInt(),
                    new BigDecimal(node.get(1).asText()),
                    node.get(2).asDouble(),
                    node.get(3).asDouble(),
                    new BigDecimal(node.get(4).asText()),
                    node.get(5).asDouble(),
                    node.get(6).asDouble(),
                    node.get(7).asInt());
        }

        throw JsonMappingException.from(p, "Invalid TickerEntry format");
    }
}
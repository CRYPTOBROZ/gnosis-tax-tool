package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.io.IOException;

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
            KrakenTickerEntry entry = new KrakenTickerEntry();
            entry.setTimestamp(node.get(0).asInt());
            entry.setOpen(node.get(1).decimalValue());
            entry.setHigh(node.get(2).asDouble());
            entry.setLow(node.get(3).asDouble());
            entry.setClose(node.get(4).decimalValue());
            entry.setVolume(node.get(5).asDouble());
            entry.setQuoteVolume(node.get(6).asDouble());
            entry.setCount(node.get(7).asInt());
            return entry;
        }

        throw JsonMappingException.from(p, "Invalid TickerEntry format");
    }
}
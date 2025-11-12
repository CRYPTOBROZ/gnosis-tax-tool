package com.cryptobroz.gnosis_tax_tool.services.KrakenService.dto;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TickerEntryDeserializer extends JsonDeserializer<TickerEntry> {
    @Override
    public TickerEntry deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        List<Object> list = mapper.readValue(p, List.class);

        TickerEntry entry = new TickerEntry();
        entry.setTimestamp(Long.parseLong(list.get(0).toString()));
        entry.setOpen(Double.parseDouble(list.get(1).toString()));
        entry.setHigh(Double.parseDouble(list.get(2).toString()));
        entry.setLow(Double.parseDouble(list.get(3).toString()));
        entry.setClose(Double.parseDouble(list.get(4).toString()));
        entry.setVolume(Double.parseDouble(list.get(5).toString()));
        entry.setQuoteVolume(Double.parseDouble(list.get(6).toString()));
        entry.setCount(Integer.parseInt(list.get(7).toString()));
        return entry;
    }
}
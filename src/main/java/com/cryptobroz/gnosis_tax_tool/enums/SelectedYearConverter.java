package com.cryptobroz.gnosis_tax_tool.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SelectedYearConverter implements Converter<String, SelectedYear> {
  @Override
  public SelectedYear convert(String source) {
    if (source == null) {
      return null;
    }
    try {
      return SelectedYear.valueOf(source.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
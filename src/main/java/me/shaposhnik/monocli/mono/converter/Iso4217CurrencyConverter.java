package me.shaposhnik.monocli.mono.converter;

import java.util.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class Iso4217CurrencyConverter implements Converter<Integer, String> {
  @Override
  public String convert(Integer numericCode) {
    return Currency.getAvailableCurrencies().stream()
        .filter(currency -> currency.getNumericCode() == numericCode)
        .map(Currency::getCurrencyCode)
        .findFirst()
        .orElseThrow();
  }
}

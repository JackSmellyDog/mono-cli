package me.shaposhnik.monocli.mono.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Currency;

public class Iso4217CurrencyDeserializer extends JsonDeserializer<String> {

  @Override
  public String deserialize(JsonParser parser, DeserializationContext deserializationContext)
      throws IOException {
    int numericCode = parser.getIntValue();

    return Currency.getAvailableCurrencies().stream()
        .filter(currency -> currency.getNumericCode() == numericCode)
        .map(Currency::getCurrencyCode)
        .findFirst()
        .orElseThrow();
  }
}

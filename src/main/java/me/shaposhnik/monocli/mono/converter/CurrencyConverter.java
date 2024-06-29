package me.shaposhnik.monocli.mono.converter;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Currency;
import me.shaposhnik.monocli.mono.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CurrencyConverter implements Converter<CurrencyDto, Currency> {
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;
  private final UnixEpochTimeConverter unixEpochTimeConverter;

  @Override
  public Currency convert(CurrencyDto source) {
    return new Currency(
        iso4217CurrencyConverter.convert(source.currencyCodeA()),
        iso4217CurrencyConverter.convert(source.currencyCodeB()),
        unixEpochTimeConverter.convert(source.date()),
        source.rateSell(),
        source.rateBuy(),
        source.rateCross()
    );
  }
}

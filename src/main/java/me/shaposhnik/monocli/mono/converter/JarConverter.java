package me.shaposhnik.monocli.mono.converter;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Jar;
import me.shaposhnik.monocli.mono.dto.JarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class JarConverter implements Converter<JarDto, Jar> {
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;

  @Override
  public Jar convert(JarDto source) {
    return new Jar(
        source.id(),
        source.sendId(),
        source.title(),
        source.description(),
        iso4217CurrencyConverter.convert(source.currencyCode()),
        source.balance(),
        source.goal()
    );
  }

  public List<Jar> convert(List<JarDto> sourceList) {
    if (sourceList == null) {
      return Collections.emptyList();
    }
    return sourceList.stream()
        .map(this::convert)
        .toList();
  }
}

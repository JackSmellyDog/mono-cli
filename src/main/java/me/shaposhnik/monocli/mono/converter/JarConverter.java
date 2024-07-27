package me.shaposhnik.monocli.mono.converter;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Jar;
import me.shaposhnik.monocli.mono.dto.JarDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class JarConverter implements ListConverter<JarDto, Jar> {
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;

  @Override
  public Jar convert(JarDto source) {
    return Jar.builder()
        .id(source.id())
        .sendId(source.sendId())
        .title(source.title())
        .description(source.description())
        .currencyCode(iso4217CurrencyConverter.convert(source.currencyCode()))
        .balance(source.balance())
        .goal(source.goal())
        .build();
  }
}

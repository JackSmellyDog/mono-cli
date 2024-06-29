package me.shaposhnik.monocli.mono.converter;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
class ConversionConfig {
  private final Set<Converter<?, ?>> converters;

  @Bean
  public ConversionService conversionService() {
    var conversionService = new DefaultConversionService();
    converters.forEach(conversionService::addConverter);

    return conversionService;
  }
}

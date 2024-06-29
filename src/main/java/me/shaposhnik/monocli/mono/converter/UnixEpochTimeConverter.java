package me.shaposhnik.monocli.mono.converter;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UnixEpochTimeConverter implements Converter<Long, LocalDateTime> {
  private final Clock clock;

  @Override
  public LocalDateTime convert(Long seconds) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), clock.getZone());
  }
}

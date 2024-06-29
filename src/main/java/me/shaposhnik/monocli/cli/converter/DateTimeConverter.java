package me.shaposhnik.monocli.cli.converter;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.TypeConversionException;
import picocli.CommandLine.ITypeConverter;

@Component
@RequiredArgsConstructor
public class DateTimeConverter implements ITypeConverter<String> {
  private static final DateTimeFormatter MEGA_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
      .appendOptional(ofPattern("yyyy-MM-dd HH:mm:ss.SSSZZZZZ"))
      .appendOptional(ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ss.S'Z'"))
      .appendOptional(ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ"))
      .appendOptional(ofPattern("yyyy-MM-dd HH:mm:ss"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ssz"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ"))
      .appendOptional(ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
      .appendOptional(ofPattern("EEE MMM dd HH:mm:ss zzz yyyy"))
      .appendOptional(ofPattern("yyyy-MM-dd"))
      .appendOptional(ofPattern("dd-MM-yyyy"))
      .appendOptional(ofPattern("MMMM d[d] yyyy"))
      .toFormatter();

  private final Clock clock;

  @Override
  public String convert(String s) throws Exception {
    if (s == null || s.isEmpty() || canParseLong(s)) {
      return s;
    }

    Instant instant = switch (s) {
      case "now" -> now();
      case "today" -> today();
      default -> parse(s);
    };
    return String.valueOf(instant.getEpochSecond());
  }

  private Instant now() {
    return ZonedDateTime.now(clock).toInstant();
  }

  private Instant today() {
    return LocalDate.now(clock).atStartOfDay(clock.getZone()).toInstant();
  }

  private boolean canParseLong(String s) {
    try {
      Long.valueOf(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private Instant parse(String s) {
    try {
      return parseDateTime(s);
    } catch (DateTimeParseException e) {
      throw new TypeConversionException(e.getMessage());
    }
  }

  private Instant parseDateTime(String s) {
    try {
      return LocalDateTime.parse(s, MEGA_DATE_TIME_FORMATTER).atZone(clock.getZone()).toInstant();
    } catch (DateTimeParseException e) {
      return LocalDate.parse(s, MEGA_DATE_TIME_FORMATTER).atStartOfDay(clock.getZone()).toInstant();
    }
  }
}

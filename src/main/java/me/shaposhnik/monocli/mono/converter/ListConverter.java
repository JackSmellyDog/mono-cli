package me.shaposhnik.monocli.mono.converter;

import java.util.Collections;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

interface ListConverter<T, R> extends Converter<T, R> {
  default List<R> convert(List<T> sourceList) {
    if (sourceList == null) {
      return Collections.emptyList();
    }
    return sourceList.stream()
        .map(this::convert)
        .toList();
  }
}

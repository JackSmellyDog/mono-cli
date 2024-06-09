package me.shaposhnik.monocli.mono.dto;

import static java.util.Collections.emptyList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record MonoApiResponse<T>(String nativeBody,
                                 T body,
                                 List<String> errors,
                                 List<String> warnings) {

  public MonoApiResponse {
    Objects.requireNonNull(errors);
    Objects.requireNonNull(warnings);

    if (body != null && !errors.isEmpty()) {
      throw new IllegalArgumentException(
          "ApiResponse can't have body and errors at the same time.");
    }

    if (body == null && errors.isEmpty()) {
      throw new IllegalArgumentException(
          "ApiResponse can't have body and errors empty at the same time.");
    }
  }

  public MonoApiResponse(String nativeBody, T body) {
    this(nativeBody, body, emptyList(), emptyList());
  }

  public MonoApiResponse(String nativeBody, T body, List<String> warnings) {
    this(nativeBody, body, emptyList(),
        Objects.requireNonNullElseGet(warnings, Collections::emptyList));
  }

  public MonoApiResponse(List<String> errors) {
    this(null, null, errors, emptyList());
  }

}

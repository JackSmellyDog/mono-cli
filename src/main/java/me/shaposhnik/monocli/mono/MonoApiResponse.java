package me.shaposhnik.monocli.mono;

import static java.util.Collections.emptyList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record MonoApiResponse<T>(String nativeBody,
                                 T body,
                                 String error,
                                 List<String> warnings) {

  public MonoApiResponse {
    Objects.requireNonNull(warnings);

    if (body != null && error != null) {
      throw new IllegalStateException(
          "ApiResponse can't have body and error at the same time.");
    }

    if (body == null && error == null) {
      throw new IllegalStateException(
          "ApiResponse can't have body and error empty at the same time.");
    }
  }

  public MonoApiResponse(String nativeBody, T body) {
    this(nativeBody, body, null, emptyList());
  }

  public MonoApiResponse(String nativeBody, T body, List<String> warnings) {
    this(nativeBody, body, null,
        Objects.requireNonNullElseGet(warnings, Collections::emptyList));
  }

  public MonoApiResponse(String error) {
    this(null, null, error, emptyList());
  }

}

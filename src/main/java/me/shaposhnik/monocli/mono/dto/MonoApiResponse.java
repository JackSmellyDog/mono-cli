package me.shaposhnik.monocli.mono.dto;

import java.util.List;

public record MonoApiResponse<T>(String nativeBody,
                                 T body,
                                 List<String> errors,
                                 List<String> warnings) {

  public MonoApiResponse {
    if (body != null && errors != null && !errors.isEmpty()) {
      throw new IllegalArgumentException(
          "ApiResponse can't have body and errors at the same time.");
    }

    if (body == null && (errors == null || errors.isEmpty())) {
      throw new IllegalArgumentException(
          "ApiResponse can't have body and errors empty at the same time.");
    }
  }

  public MonoApiResponse(String nativeBody, T body) {
    this(nativeBody, body, null, null);
  }
  public MonoApiResponse(String nativeBody, T body, List<String> warnings) {
    this(nativeBody, body, null, warnings);
  }
  public MonoApiResponse(List<String> errors) {
    this(null, null, errors, null);
  }

}

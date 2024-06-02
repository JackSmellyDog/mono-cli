package me.shaposhnik.monocli.cli.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

@RequiredArgsConstructor
public abstract class AbstractCommandLineView<T> implements CommandLineView {
  protected final MonoApiResponse<T> response;
  protected final ObjectMapper objectMapper;

  @Override
  public String toNative() {
    return response.nativeBody();
  }

  @Override
  public String toJson() {
    try {
      return objectMapper.writeValueAsString(response.body());
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

}

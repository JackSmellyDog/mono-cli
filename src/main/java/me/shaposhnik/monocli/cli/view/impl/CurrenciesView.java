package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

@RequiredArgsConstructor
public class CurrenciesView implements CommandLineView {

  private final MonoApiResponse<List<Currency>> response;
  private final ObjectMapper objectMapper;

  @Override
  public String toNative() {
    return response.nativeBody();
  }

  @Override
  public String toJson() {
    try {
      return objectMapper.writeValueAsString(response.body());
    } catch (JsonProcessingException e) {
      // todo: handle to return a value
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toTable() {
    return null;
  }
}

package me.shaposhnik.monocli.cli.view.impl;

import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.makeRed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.CommandLineView;

@RequiredArgsConstructor
public class ErrorView implements CommandLineView {
  private final String error;
  private final ObjectMapper objectMapper;

  @Override
  public String toNative() {
    return createJson();
  }

  @Override
  public String toJson() {
    return createJson();
  }

  @Override
  public String toViewable() {
    return makeRed(error);
  }

  private String createJson() {
    try {
      return objectMapper.writeValueAsString(new Error(error));
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  private record Error(String error) {

  }
}

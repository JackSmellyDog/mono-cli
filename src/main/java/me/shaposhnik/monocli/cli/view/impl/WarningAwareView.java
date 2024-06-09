package me.shaposhnik.monocli.cli.view.impl;

import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.makeYellow;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.CommandLineView;

@RequiredArgsConstructor
public class WarningAwareView implements CommandLineView {
  private final CommandLineView wrapped;
  private final List<String> warnings;
  private final ObjectMapper objectMapper;

  @Override
  public String toNative() {
    try {
      return objectMapper.writeValueAsString(new JsonWarningAware(wrapped.toNative(), warnings));
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String toJson() {
    try {
      return objectMapper.writeValueAsString(new JsonWarningAware(wrapped.toJson(), warnings));
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String toViewable() {
    if (warnings.isEmpty()) {
      return wrapped.toViewable();
    }

    final String warningsMessage = String.join("\n", warnings);
    return "%s%n%n%s%n%n%s".formatted(
        makeYellow(warningsMessage),
        wrapped.toViewable(),
        makeYellow(warningsMessage)
    );
  }

  record JsonWarningAware(@JsonRawValue String content, List<String> warnings) {
  }
}

package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

public class CurrenciesView extends AbstractCommandLineView<List<Currency>> {

  public CurrenciesView(MonoApiResponse<List<Currency>> response, ObjectMapper objectMapper) {
    super(response, objectMapper);
  }

  @Override
  public String toTable() {
    return null;
  }
}

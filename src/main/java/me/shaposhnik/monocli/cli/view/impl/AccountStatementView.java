package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;

public class AccountStatementView extends AbstractCommandLineView<List<Transaction>> {

  public AccountStatementView(MonoApiResponse<List<Transaction>> response,
                              ObjectMapper objectMapper) {
    super(response, objectMapper);
  }

  @Override
  public String toTable() {
    return null;
  }
}

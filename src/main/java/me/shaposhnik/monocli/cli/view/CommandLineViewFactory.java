package me.shaposhnik.monocli.cli.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.impl.AccountStatementView;
import me.shaposhnik.monocli.cli.view.impl.CurrenciesView;
import me.shaposhnik.monocli.cli.view.impl.UserInfoView;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineViewFactory {
  private final ObjectMapper objectMapper;
  private final YAMLMapper yamlMapper;

  public CommandLineView createUserInfoView(MonoApiResponse<ClientInfo> response) {
      return new UserInfoView(response, objectMapper, yamlMapper);
  }

  public CommandLineView createCurrencyView(MonoApiResponse<List<Currency>> response) {
    return new CurrenciesView(response, objectMapper);
  }

  public CommandLineView createAccountStatementView(MonoApiResponse<List<Transaction>> response) {
    return new AccountStatementView(response, objectMapper);
  }
}

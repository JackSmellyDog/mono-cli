package me.shaposhnik.monocli.cli.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.impl.*;
import me.shaposhnik.monocli.core.ClientInfo;
import me.shaposhnik.monocli.core.Currency;
import me.shaposhnik.monocli.core.Transaction;
import me.shaposhnik.monocli.mono.MonoApiResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineViewFactory {
  private final ObjectMapper objectMapper;
  private final YAMLMapper yamlMapper;

  public CommandLineView createUserInfoView(MonoApiResponse<ClientInfo> response,
                                            boolean isWarningsAware) {
    return createView(() -> new UserInfoView(response, objectMapper, yamlMapper),
        response.error(), response.warnings(), isWarningsAware);
  }

  public CommandLineView createCurrencyView(MonoApiResponse<List<Currency>> response,
                                            boolean isWarningsAware) {
    return createView(() -> new CurrenciesView(response, objectMapper),
        response.error(), response.warnings(), isWarningsAware);
  }

  public CommandLineView createAccountStatementView(MonoApiResponse<List<Transaction>> response,
                                                    boolean isWarningsAware) {
    return createView(() -> new AccountStatementView(response, objectMapper),
        response.error(), response.warnings(), isWarningsAware);
  }

  private CommandLineView createView(Supplier<CommandLineView> supplier,
                                     String error,
                                     List<String> warnings,
                                     boolean isWarningsAware) {
    if (error != null) {
      return new ErrorView(error, objectMapper);
    }

    var view = supplier.get();
    return isWarningsAware ? new WarningAwareView(view, warnings, objectMapper) : view;
  }
}

package me.shaposhnik.monocli.cli.command;

import java.util.List;
import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.mono.client.MonoHttpClient;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
public class CurrencyCommand extends AbstractMonoApiCommand<List<Currency>> {

  public CurrencyCommand(MonoHttpClient monoHttpClient, CommandLineViewFactory viewFactory) {
    super(monoHttpClient, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<List<Currency>> response,
                                       boolean disableWarnings) {
    return viewFactory.createCurrencyView(response, !disableWarnings);
  }

  @Override
  protected MonoApiResponse<List<Currency>> retrieveResponse() {
    return monoHttpClient.getCurrencies();
  }
}

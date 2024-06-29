package me.shaposhnik.monocli.cli.command;

import java.util.List;
import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.core.Currency;
import me.shaposhnik.monocli.mono.MonoApiResponse;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
class CurrencyCommand extends AbstractMonoApiCommand<List<Currency>> {

  public CurrencyCommand(MonoService service, CommandLineViewFactory viewFactory) {
    super(service, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<List<Currency>> response,
                                       boolean disableWarnings) {
    return viewFactory.createCurrencyView(response, !disableWarnings);
  }

  @Override
  protected MonoApiResponse<List<Currency>> retrieveResponse() {
    return service.getCurrencies();
  }
}

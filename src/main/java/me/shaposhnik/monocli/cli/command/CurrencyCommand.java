package me.shaposhnik.monocli.cli.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.ConsoleViewUtils;
import me.shaposhnik.monocli.cli.view.impl.CurrenciesView;
import me.shaposhnik.monocli.mono.MonoService;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
@RequiredArgsConstructor
public class CurrencyCommand implements Callable<Integer> {

  private final MonoService monoService;
  private final ObjectMapper objectMapper;

  @Override
  public Integer call() throws Exception {
    MonoApiResponse<List<Currency>> currencies = monoService.getCurrencies();
    var view = new CurrenciesView(currencies, objectMapper);

    ConsoleViewUtils.printLine(view.toJson());
    ConsoleViewUtils.printLine(view.toNative());

    return 0;
  }
}

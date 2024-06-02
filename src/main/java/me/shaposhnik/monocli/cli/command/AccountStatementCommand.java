package me.shaposhnik.monocli.cli.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.ConsoleViewUtils;
import me.shaposhnik.monocli.cli.view.impl.AccountStatementView;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "statement")
@RequiredArgsConstructor
public class AccountStatementCommand implements Callable<Integer> {

  private final MonoService monoService;
  private final ObjectMapper objectMapper;

  @Option(names = {"--account", "-a"})
  private String account;

  @Option(names = {"--from", "-f"})
  private String from;

  @Option(names = {"--to", "-t"})
  private String to;

  @Override
  public Integer call() throws Exception {
    var transactions = monoService.getTransactions(account, from, to);
    var view = new AccountStatementView(transactions, objectMapper);

    ConsoleViewUtils.printLine(view.toJson());
    ConsoleViewUtils.printLine(view.toNative());

    return 0;
  }
}

package me.shaposhnik.monocli.cli.command;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.cli.view.ConsoleViewUtils;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "statement")
@RequiredArgsConstructor
public class AccountStatementCommand implements Callable<Integer> {

  private final MonoService monoService;
  private final CommandLineViewFactory viewFactory;
  @Option(names = {"--account", "-a"})
  private String account;

  @Option(names = {"--from", "-f"})
  private String from;

  @Option(names = {"--to", "-t"})
  private String to;

  @Override
  public Integer call() throws Exception {
    var transactions = monoService.getTransactions(account, from, to);
    var view = viewFactory.createAccountStatementView(transactions);

    ConsoleViewUtils.printLine(view.toJson());
    ConsoleViewUtils.printLine(view.toNative());
    ConsoleViewUtils.printLine(view.toViewable());

    return 0;
  }
}

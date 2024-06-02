package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "statement")
@RequiredArgsConstructor
public class AccountStatementCommand implements Callable<Integer> {

  private final MonoService monoService;

  @Option(names = {"--account", "-a"})
  private String account;

  @Option(names = {"--from", "-f"})
  private String from;

  @Option(names = {"--to", "-t"})
  private String to;

  @Override
  public Integer call() throws Exception {
    System.out.println(monoService.getTransactions(account, from, to));

    return 0;
  }
}

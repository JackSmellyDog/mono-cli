package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.mono.client.MonoPrivateClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "statement")
public class AccountStatementCommand implements Callable<Integer> {

  private final MonoPrivateClient client;

  @Option(names = {"--account", "-a"})
  private String account;

  @Option(names = {"--from", "-f"})
  private String from;

  @Option(names = {"--to", "-t"})
  private String to;

  public AccountStatementCommand(MonoPrivateClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    System.out.println(client.getTransactions(account, from, to));

    return 0;
  }
}

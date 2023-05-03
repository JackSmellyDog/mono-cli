package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.integration.client.MonoClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
public class CurrencyCommand implements Callable<Integer> {

  private final MonoClient client;

  public CurrencyCommand(MonoClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    client.getCurrencies().forEach(System.out::println);

    return 0;
  }
}

package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.mono.client.MonoPublicClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
public class CurrencyCommand implements Callable<Integer> {

  private final MonoPublicClient client;

  public CurrencyCommand(MonoPublicClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    client.getCurrencies().forEach(System.out::println);

    return 0;
  }
}

package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.integration.client.MonoClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "info")
public class ClientInfoCommand implements Callable<Integer> {

  private final MonoClient client;

  @Option(names = {"--token", "-X"})
  private String token;

  public ClientInfoCommand(MonoClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    System.out.println(client.getClientInfo(token));

    return 0;
  }
}

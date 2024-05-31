package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.mono.client.MonoPrivateClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
public class ClientInfoCommand implements Callable<Integer> {

  private final MonoPrivateClient client;

  public ClientInfoCommand(MonoPrivateClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    System.out.println(client.getClientInfo());

    return 0;
  }
}

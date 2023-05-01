package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import me.shaposhnik.monocli.integration.client.MonoClient;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "mono", mixinStandardHelpOptions = true)
public class MonoCommand implements Callable<Integer> {

  private final MonoClient client;

  public MonoCommand(MonoClient client) {
    this.client = client;
  }

  @Override
  public Integer call() throws Exception {
    return 0;
  }

}

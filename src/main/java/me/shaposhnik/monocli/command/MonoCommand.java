package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "mono",
    subcommands = {
        CurrencyCommand.class,
        ClientInfoCommand.class,
        AccountStatementCommand.class
    },
    mixinStandardHelpOptions = true
)
public class MonoCommand implements Callable<Integer> {

  @Override
  public Integer call() {
    return 0;
  }
}

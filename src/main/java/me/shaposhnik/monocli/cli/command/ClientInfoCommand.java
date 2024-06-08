package me.shaposhnik.monocli.cli.command;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.cli.view.ConsoleViewUtils;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
@RequiredArgsConstructor
public class ClientInfoCommand implements Callable<Integer> {

  private final MonoService monoService;
  private final CommandLineViewFactory viewFactory;

  @Override
  public Integer call() throws Exception {
    var clientInfo = monoService.getClientInfo();
    var view = viewFactory.createUserInfoView(clientInfo);

    ConsoleViewUtils.printLine(view.toJson());
    ConsoleViewUtils.printLine(view.toNative());
    ConsoleViewUtils.printLine(view.toViewable());

    return 0;
  }
}

package me.shaposhnik.monocli.cli.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.view.ConsoleViewUtils;
import me.shaposhnik.monocli.cli.view.impl.UserInfoView;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
@RequiredArgsConstructor
public class ClientInfoCommand implements Callable<Integer> {

  private final MonoService monoService;
  private final ObjectMapper objectMapper;
  private final YAMLMapper yamlMapper;

  @Override
  public Integer call() throws Exception {
    var clientInfo = monoService.getClientInfo();
    var view = new UserInfoView(clientInfo, objectMapper, yamlMapper);

    ConsoleViewUtils.printLine(view.toJson());
    ConsoleViewUtils.printLine(view.toNative());
    ConsoleViewUtils.printLine(view.toViewable());

    return 0;
  }
}

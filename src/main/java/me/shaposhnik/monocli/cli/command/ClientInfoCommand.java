package me.shaposhnik.monocli.cli.command;

import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.mono.MonoService;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
public class ClientInfoCommand extends AbstractMonoApiCommand<ClientInfo> {

  public ClientInfoCommand(MonoService service, CommandLineViewFactory viewFactory) {
    super(service, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<ClientInfo> response) {
    return viewFactory.createUserInfoView(response);
  }

  @Override
  protected MonoApiResponse<ClientInfo> retrieveResponse() {
    return service.getClientInfo();
  }
}

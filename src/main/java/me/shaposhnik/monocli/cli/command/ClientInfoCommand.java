package me.shaposhnik.monocli.cli.command;

import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.core.ClientInfo;
import me.shaposhnik.monocli.mono.MonoApiResponse;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
class ClientInfoCommand extends AbstractMonoApiCommand<ClientInfo> {

  public ClientInfoCommand(MonoService service, CommandLineViewFactory viewFactory) {
    super(service, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<ClientInfo> response,
                                       boolean disableWarnings) {
    return viewFactory.createUserInfoView(response, !disableWarnings);
  }

  @Override
  protected MonoApiResponse<ClientInfo> retrieveResponse() {
    return service.getClientInfo();
  }
}

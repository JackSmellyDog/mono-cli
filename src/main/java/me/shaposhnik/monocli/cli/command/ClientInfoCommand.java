package me.shaposhnik.monocli.cli.command;

import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.mono.client.MonoHttpClient;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "info")
public class ClientInfoCommand extends AbstractMonoApiCommand<ClientInfo> {

  public ClientInfoCommand(MonoHttpClient monoHttpClient, CommandLineViewFactory viewFactory) {
    super(monoHttpClient, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<ClientInfo> response,
                                       boolean disableWarnings) {
    return viewFactory.createUserInfoView(response, !disableWarnings);
  }

  @Override
  protected MonoApiResponse<ClientInfo> retrieveResponse() {
    return monoHttpClient.getClientInfo();
  }
}

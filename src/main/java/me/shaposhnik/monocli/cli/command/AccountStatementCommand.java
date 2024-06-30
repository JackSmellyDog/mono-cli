package me.shaposhnik.monocli.cli.command;

import java.util.List;
import me.shaposhnik.monocli.cli.converter.DateTimeConverter;
import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.core.Transaction;
import me.shaposhnik.monocli.mono.MonoApiResponse;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "statement", aliases = {"transactions", "st", "t"}, sortOptions = false)
class AccountStatementCommand extends AbstractMonoApiCommand<List<Transaction>> {

  @Option(names = {"--account", "-a"}, defaultValue = "0")
  private String account;

  @Option(names = {"--from", "-f"}, required = true, converter = DateTimeConverter.class)
  private String from;

  @Option(names = {"--to", "-t"}, defaultValue = "", converter = DateTimeConverter.class)
  private String to;

  public AccountStatementCommand(MonoService service,
                                 CommandLineViewFactory viewFactory) {
    super(service, viewFactory);
  }

  @Override
  protected CommandLineView createView(MonoApiResponse<List<Transaction>> response,
                                       boolean disableWarnings) {
    return viewFactory.createAccountStatementView(response, !disableWarnings);
  }

  @Override
  protected MonoApiResponse<List<Transaction>> retrieveResponse() {
    return service.getTransactions(account, from, to);
  }
}

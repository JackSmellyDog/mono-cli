package me.shaposhnik.monocli.cli.command;

import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.printLine;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.option.OutputOptions;
import me.shaposhnik.monocli.cli.view.CommandLineView;
import me.shaposhnik.monocli.cli.view.CommandLineViewFactory;
import me.shaposhnik.monocli.mono.MonoService;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import picocli.CommandLine.Mixin;

@RequiredArgsConstructor
public abstract class AbstractMonoApiCommand<T> implements Callable<Integer> {
  protected final MonoService service;
  protected final CommandLineViewFactory viewFactory;
  @Mixin
  protected final OutputOptions outputOptions = new OutputOptions();

  @Override
  public Integer call() {
    var response = retrieveResponse();
    var view = createView(response);

    switch (outputOptions.getFormatting()) {
      case JSON -> printLine(view.toJson());
      case NATIVE -> printLine(view.toNative());
      case VIEWABLE -> printLine(view.toViewable());
    }

    return 0;
  }

  protected abstract CommandLineView createView(MonoApiResponse<T> response);

  protected abstract MonoApiResponse<T> retrieveResponse();
}

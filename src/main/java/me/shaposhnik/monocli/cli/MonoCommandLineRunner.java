package me.shaposhnik.monocli.cli;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.cli.command.MonoCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
@RequiredArgsConstructor
public class MonoCommandLineRunner implements CommandLineRunner, ExitCodeGenerator {

  private final MonoCommand monoCommand;
  private final IFactory factory;
  private int exitCode;

  @Override
  public void run(String... args) {
    exitCode = new CommandLine(monoCommand, factory)
        .setCaseInsensitiveEnumValuesAllowed(true)
        .execute(args);
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }
}

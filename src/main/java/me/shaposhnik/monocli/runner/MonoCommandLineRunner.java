package me.shaposhnik.monocli.runner;

import me.shaposhnik.monocli.command.MonoCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class MonoCommandLineRunner implements CommandLineRunner, ExitCodeGenerator {

  private final MonoCommand monoCommand;
  private final IFactory factory;
  private int exitCode;

  public MonoCommandLineRunner(MonoCommand monoCommand, IFactory factory) {
    this.monoCommand = monoCommand;
    this.factory = factory;
  }

  @Override
  public void run(String... args) throws Exception {
    exitCode = new CommandLine(monoCommand, factory).execute(args);
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }
}

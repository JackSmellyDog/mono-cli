package me.shaposhnik.monocli.cli.option;

import lombok.Getter;
import picocli.CommandLine.Option;

@Getter
public class OutputOptions {

  @Option(names = {"--output", "-o"}, defaultValue = "viewable")
  private FormattingOption formatting;

}

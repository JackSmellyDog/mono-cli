package me.shaposhnik.monocli.cli.option;

import lombok.Getter;
import picocli.CommandLine.Option;

@Getter
public class OutputOptions {

  @Option(names = {"--output", "-o"}, defaultValue = "viewable", order = Integer.MAX_VALUE)
  private FormattingOption formatting;

  @Option(names = {"--disable-warnings", "-W"})
  private boolean disableWarnings;

}

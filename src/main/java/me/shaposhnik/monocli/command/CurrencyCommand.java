package me.shaposhnik.monocli.command;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.mono.MonoService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "currency")
@RequiredArgsConstructor
public class CurrencyCommand implements Callable<Integer> {

  private final MonoService monoService;

  @Override
  public Integer call() throws Exception {
    monoService.getCurrencies().forEach(System.out::println);

    return 0;
  }
}

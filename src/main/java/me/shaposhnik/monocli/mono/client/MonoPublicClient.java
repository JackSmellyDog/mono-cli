package me.shaposhnik.monocli.mono.client;

import java.util.List;
import me.shaposhnik.monocli.mono.dto.Currency;
import org.springframework.web.service.annotation.GetExchange;

public interface MonoPublicClient {
  @GetExchange("/bank/currency")
  List<Currency> getCurrencies();
}

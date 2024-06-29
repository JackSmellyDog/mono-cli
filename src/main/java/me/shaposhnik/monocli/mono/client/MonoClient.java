package me.shaposhnik.monocli.mono.client;

import java.util.List;
import me.shaposhnik.monocli.mono.dto.ClientInfoDto;
import me.shaposhnik.monocli.mono.dto.CurrencyDto;
import me.shaposhnik.monocli.mono.dto.TransactionDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("https://api.monobank.ua")
public interface MonoClient {

  String X_TOKEN = "X-Token";

  @GetExchange("/bank/currency")
  List<CurrencyDto> getCurrencies();

  @GetExchange("/personal/client-info")
  ClientInfoDto getClientInfo(@RequestHeader(X_TOKEN) String token);

  @GetExchange("/personal/statement/{account}/{from}/{to}")
  List<TransactionDto> getTransactions(@RequestHeader(X_TOKEN) String token,
                                       @PathVariable String account,
                                       @PathVariable String from,
                                       @PathVariable String to);
}

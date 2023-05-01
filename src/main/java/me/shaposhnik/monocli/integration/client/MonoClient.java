package me.shaposhnik.monocli.integration.client;

import java.util.List;
import me.shaposhnik.monocli.integration.dto.ClientInfo;
import me.shaposhnik.monocli.integration.dto.Currency;
import me.shaposhnik.monocli.integration.dto.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

public interface MonoClient {

  @GetExchange("/bank/currency")
  List<Currency> getCurrencies();

  @GetExchange("/personal/client-info")
  ClientInfo getClientInfo(@RequestHeader("X-Token") String token);

  @GetExchange("/personal/statement/{account}/{from}/{to}")
  List<Transaction> getTransactions(@RequestHeader("X-Token") String token,
                                    @PathVariable String account,
                                    @PathVariable String from,
                                    @PathVariable(required = false) String to);

}

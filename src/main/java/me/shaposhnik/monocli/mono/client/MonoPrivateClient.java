package me.shaposhnik.monocli.mono.client;

import java.util.List;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface MonoPrivateClient {

  @GetExchange("/personal/client-info")
  ClientInfo getClientInfo();

  @GetExchange("/personal/statement/{account}/{from}/{to}")
  List<Transaction> getTransactions(@PathVariable String account,
                                    @PathVariable String from,
                                    @PathVariable(required = false) String to);

}

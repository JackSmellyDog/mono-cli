package me.shaposhnik.monocli.mono;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.mono.client.MonoHttpClient;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.Transaction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonoService {

  private final MonoHttpClient httpClient;

  public List<Currency> getCurrencies() {
    return httpClient.getCurrencies().body();
  }

  public ClientInfo getClientInfo() {
    return httpClient.getClientInfo().body();
  }

  public List<Transaction> getTransactions(String account, String form, String to) {
    return httpClient.getTransactions(account, form, to).body();
  }

}

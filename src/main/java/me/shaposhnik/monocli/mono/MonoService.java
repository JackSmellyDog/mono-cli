package me.shaposhnik.monocli.mono;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.mono.client.MonoHttpClient;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonoService {

  private final MonoHttpClient httpClient;

  public MonoApiResponse<List<Currency>> getCurrencies() {
    try {
      return httpClient.getCurrencies();
    } catch (Exception e) {
      return new MonoApiResponse<>(List.of(e.getMessage()));
    }
  }

  public MonoApiResponse<ClientInfo> getClientInfo() {
    try {
      return httpClient.getClientInfo();
    } catch (Exception e) {
      return new MonoApiResponse<>(List.of(e.getMessage()));
    }
  }

  public MonoApiResponse<List<Transaction>> getTransactions(String account, String form,
                                                            String to) {
    try {
      return httpClient.getTransactions(account, form, to);
    } catch (Exception e) {
      return new MonoApiResponse<>(List.of(e.getMessage()));
    }
  }

}

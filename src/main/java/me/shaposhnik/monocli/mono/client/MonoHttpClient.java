package me.shaposhnik.monocli.mono.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.shaposhnik.monocli.mono.MonoProperties;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MonoHttpClient {
  private final RestClient restClient;
  private final MonoProperties properties;
  private final ObjectMapper objectMapper;

  public MonoHttpClient(RestClient.Builder restClientBuilder,
                        MonoProperties properties,
                        ObjectMapper objectMapper) {
    this.restClient = restClientBuilder.build();
    this.properties = properties;
    this.objectMapper = objectMapper;
  }

  public MonoApiResponse<List<Currency>> getCurrencies() {
    String nativeBody = restClient.get()
        .uri(properties.endpoints().currency())
        .retrieve()
        .body(String.class);

    try {
      List<Currency> currencies = objectMapper.readValue(nativeBody, new TypeReference<>() {
      });
      return new MonoApiResponse<>(nativeBody, currencies);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public MonoApiResponse<ClientInfo> getClientInfo() {
    String nativeBody = restClient.get()
        .uri(properties.endpoints().clientInfo())
        .header("X-Token", properties.xToken())
        .retrieve()
        .body(String.class);

    try {
      var clientInfo = objectMapper.readValue(nativeBody, ClientInfo.class);
      return new MonoApiResponse<>(nativeBody, clientInfo);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public MonoApiResponse<List<Transaction>> getTransactions(String account, String from,
                                                            String to) {
    String nativeBody = restClient.get()
        .uri(properties.endpoints().statement(), account, from, to)
        .header("X-Token", properties.xToken())
        .retrieve()
        .body(String.class);

    try {
      List<Transaction> transactions = objectMapper.readValue(nativeBody, new TypeReference<>() {
      });
      return new MonoApiResponse<>(nativeBody, transactions);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}

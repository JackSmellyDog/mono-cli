package me.shaposhnik.monocli.mono.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import me.shaposhnik.monocli.mono.MonoProperties;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;
import me.shaposhnik.monocli.mono.exception.MonoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriTemplate;

@Component
public class MonoHttpClient {
  private static final String X_TOKEN = "X-Token";
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
    URI uri = URI.create(properties.endpoints().currency());
    String nativeBody = retrieveBodyAsString(uri);

    return mapToListResponse(nativeBody, Currency.class);
  }

  public MonoApiResponse<ClientInfo> getClientInfo() {
    URI uri = URI.create(properties.endpoints().clientInfo());
    String nativeBody = retrieveBodyAsString(uri, properties.xToken());

    return mapToResponse(nativeBody, ClientInfo.class);
  }

  public MonoApiResponse<List<Transaction>> getTransactions(String account, String from,
                                                            String to) {
    URI uri = new UriTemplate(properties.endpoints().statement()).expand(account, from, to);
    String nativeBody = retrieveBodyAsString(uri, properties.xToken());

    return this.mapToListResponse(nativeBody, Transaction.class);
  }

  private String retrieveBodyAsString(URI uri, String token) {
    return restClient.get()
        .uri(uri)
        .headers(httpHeaders -> Optional.ofNullable(token)
            .ifPresent(value -> httpHeaders.add(X_TOKEN, value)))
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
          throw new MonoException("4xxClientError");
        })
        .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
          throw new MonoException("is5xxServerError");
        })
        .body(String.class);
  }

  private String retrieveBodyAsString(URI uri) {
    return retrieveBodyAsString(uri, null);
  }

  private <T> MonoApiResponse<T> mapToResponse(String nativeBody, Class<T> tClass) {
    try {
      T body = objectMapper.readValue(nativeBody, tClass);
      return new MonoApiResponse<>(nativeBody, body);
    } catch (JsonProcessingException e) {
      throw new MonoException(e);
    }
  }

  private <T> MonoApiResponse<List<T>> mapToListResponse(String nativeBody, Class<T> tClass) {
    try {
      CollectionType collectionType = TypeFactory.defaultInstance()
          .constructCollectionType(List.class, tClass);
      List<T> body = objectMapper.readValue(nativeBody, collectionType);
      return new MonoApiResponse<>(nativeBody, body);
    } catch (JsonProcessingException e) {
      throw new MonoException(e);
    }
  }

}

package me.shaposhnik.monocli.mono.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import me.shaposhnik.monocli.mono.MonoProperties;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;
import me.shaposhnik.monocli.mono.exception.MonoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriTemplate;

@Component
public class MonoHttpClient {
  private static final String X_TOKEN = "X-Token";
  private static final String TRANSACTION_LIMIT_WARNING =
      "Transaction threshold was reached. Some transactions can be lost.";
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
    ResponseResult result = retrieveBody(uri);
    if (result.hasError()) {
      return new MonoApiResponse<>(mapToString(result.error()));
    }

    List<Currency> currencies = mapSuccessfulListResponse(result.ok(), Currency.class);
    return new MonoApiResponse<>(result.ok(), currencies);
  }

  public MonoApiResponse<ClientInfo> getClientInfo() {
    URI uri = URI.create(properties.endpoints().clientInfo());
    ResponseResult result = retrieveBody(uri, properties.xToken());
    if (result.hasError()) {
      return new MonoApiResponse<>(mapToString(result.error()));
    }

    var clientInfo = mapSuccessfulResponse(result.ok(), ClientInfo.class);
    return new MonoApiResponse<>(result.ok(), clientInfo);
  }

  public MonoApiResponse<List<Transaction>> getTransactions(String account, String from,
                                                            String to) {
    URI uri = new UriTemplate(properties.endpoints().statement()).expand(account, from, to);
    ResponseResult result = retrieveBody(uri, properties.xToken());

    if (result.hasError()) {
      return new MonoApiResponse<>(mapToString(result.error()));
    }

    List<Transaction> transactions = mapSuccessfulListResponse(result.ok(), Transaction.class);
    List<String> warnings = transactions.size() >= properties.transactionLimit()
        ? List.of(TRANSACTION_LIMIT_WARNING)
        : Collections.emptyList();

    return new MonoApiResponse<>(result.ok(), transactions, warnings);
  }

  private ResponseResult retrieveBody(URI uri, String token) {
    return restClient.get()
        .uri(uri)
        .headers(httpHeaders -> Optional.ofNullable(token)
            .ifPresent(value -> httpHeaders.add(X_TOKEN, value)))
        .exchange((req, res) -> {
          if (res.getStatusCode().isError()) {
            var problemDetail = ProblemDetail.forStatusAndDetail(
                res.getStatusCode(),
                retrieveErrorBody(res.getBody())
            );
            return ResponseResult.asError(problemDetail);
          }

          return Optional.ofNullable(res.bodyTo(String.class))
              .map(ResponseResult::ok)
              .orElse(ResponseResult.asError(
                      ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),
                          "The response body is NULL."
                      )
                  )
              );
        });
  }

  private ResponseResult retrieveBody(URI uri) {
    return retrieveBody(uri, null);
  }

  private String retrieveErrorBody(InputStream inputStream) {
    try {
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      return "Failed to retrieve the native error body";
    }
  }

  private String mapToString(ProblemDetail error) {
    return "%d: %s".formatted(error.getStatus(), error.getDetail());
  }

  private <T> T mapSuccessfulResponse(String nativeBody, Class<T> tClass) {
    try {
      return objectMapper.readValue(nativeBody, tClass);
    } catch (JsonProcessingException e) {
      throw new MonoException(e);
    }
  }

  private <T> List<T> mapSuccessfulListResponse(String nativeBody,
                                                Class<T> tClass) {
    try {
      CollectionType collectionType = TypeFactory.defaultInstance()
          .constructCollectionType(List.class, tClass);
      return objectMapper.readValue(nativeBody, collectionType);
    } catch (JsonProcessingException e) {
      throw new MonoException(e);
    }
  }

  private record ResponseResult(String ok, ProblemDetail error) {
    ResponseResult {
      if (ok != null && error != null) {
        throw new IllegalStateException("Ok and Error can't be both initialized.");
      }

      if (ok == null && error == null) {
        throw new IllegalStateException("Ok and Error can't be absent at the same time");
      }
    }

    boolean hasError() {
      return error != null;
    }

    static ResponseResult ok(String body) {
      return new ResponseResult(body, null);
    }

    static ResponseResult asError(ProblemDetail problemDetail) {
      return new ResponseResult(null, problemDetail);
    }
  }
}

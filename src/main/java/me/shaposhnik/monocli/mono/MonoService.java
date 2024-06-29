package me.shaposhnik.monocli.mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.ClientInfo;
import me.shaposhnik.monocli.core.Currency;
import me.shaposhnik.monocli.core.Transaction;
import me.shaposhnik.monocli.mono.client.MonoClient;
import me.shaposhnik.monocli.mono.dto.ClientInfoDto;
import me.shaposhnik.monocli.mono.dto.CurrencyDto;
import me.shaposhnik.monocli.mono.dto.TransactionDto;
import me.shaposhnik.monocli.mono.exception.MonoApiException;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonoService {
  private static final String TRANSACTION_LIMIT_WARNING =
      "Transaction threshold was reached. Some transactions can be lost.";
  private final MonoClient monoClient;
  private final MonoProperties properties;
  private final ObjectMapper objectMapper;
  private final ConversionService conversionService;

  public MonoApiResponse<List<Currency>> getCurrencies() {
    return errorWrapper(() -> {
      List<CurrencyDto> currencyDtos = monoClient.getCurrencies();
      String nativeBody = objectMapper.writeValueAsString(currencyDtos);
      List<Currency> currencies = convertAll(currencyDtos, Currency.class);

      return new MonoApiResponse<>(nativeBody, currencies);
    });
  }

  public MonoApiResponse<ClientInfo> getClientInfo() {
    return errorWrapper(() -> {
      ClientInfoDto clientInfoDto = monoClient.getClientInfo(properties.xToken());
      String nativeBody = objectMapper.writeValueAsString(clientInfoDto);
      ClientInfo clientInfo = conversionService.convert(clientInfoDto, ClientInfo.class);

      return new MonoApiResponse<>(nativeBody, clientInfo);
    });
  }

  public MonoApiResponse<List<Transaction>> getTransactions(String account, String from,
                                                            String to) {
    return errorWrapper(() -> {
      List<TransactionDto> transactionDtos = monoClient.getTransactions(properties.xToken(),
          account, from, to);
      String nativeBody = objectMapper.writeValueAsString(transactionDtos);
      List<Transaction> transactions = convertAll(transactionDtos, Transaction.class);
      List<String> warnings = transactions.size() >= properties.transactionLimit()
          ? List.of(TRANSACTION_LIMIT_WARNING)
          : Collections.emptyList();

      return new MonoApiResponse<>(nativeBody, transactions, warnings);
    });
  }

  private <T> MonoApiResponse<T> errorWrapper(ThrowableSupplier<MonoApiResponse<T>> supplier) {
    try {
      return supplier.get();
    } catch (MonoApiException e) {
      ProblemDetail detail = e.getProblemDetail();
      return new MonoApiResponse<>("%d: %s".formatted(detail.getStatus(), detail.getDetail()));
    } catch (Exception e) {
      return new MonoApiResponse<>(e.getMessage());
    }
  }

  private <T, S> List<T> convertAll(Collection<S> dtoList, Class<T> outCLass) {
    return dtoList.stream()
        .map(dto -> conversionService.convert(dto, outCLass))
        .toList();
  }

  private interface ThrowableSupplier<T> {
    T get() throws Exception;
  }
}

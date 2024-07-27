package me.shaposhnik.monocli.mono.converter;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Transaction;
import me.shaposhnik.monocli.mono.dto.TransactionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TransactionConverter implements Converter<TransactionDto, Transaction> {
  private final UnixEpochTimeConverter unixEpochTimeConverter;
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;

  @Override
  public Transaction convert(TransactionDto source) {
    return Transaction.builder()
        .id(source.id())
        .time(unixEpochTimeConverter.convert(source.time()))
        .description(source.description())
        .mcc(source.mcc())
        .originalMcc(source.originalMcc())
        .hold(source.hold())
        .amount(source.amount())
        .operationAmount(source.operationAmount())
        .currencyCode(iso4217CurrencyConverter.convert(source.currencyCode()))
        .commissionRate(source.commissionRate())
        .cashbackAmount(source.cashbackAmount())
        .balance(source.balance())
        .comment(source.comment())
        .receiptId(source.receiptId())
        .invoiceId(source.invoiceId())
        .counterEdrpou(source.counterEdrpou())
        .counterIban(source.counterIban())
        .counterName(source.counterName())
        .build();
  }
}

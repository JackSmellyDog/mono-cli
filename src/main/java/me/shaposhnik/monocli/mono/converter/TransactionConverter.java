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

  @Override
  public Transaction convert(TransactionDto source) {
    return new Transaction(
        source.id(),
        unixEpochTimeConverter.convert(source.time()),
        source.description(),
        source.mcc(),
        source.originalMcc(),
        source.hold(),
        source.amount(),
        source.operationAmount(),
        source.currencyCode(),
        source.commissionRate(),
        source.cashbackAmount(),
        source.balance(),
        source.comment(),
        source.receiptId(),
        source.invoiceId(),
        source.counterEdrpou(),
        source.counterIban(),
        source.counterName()
    );
  }
}

package me.shaposhnik.monocli.core;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Transaction(
    String id,
    LocalDateTime time,
    String description,
    Integer mcc,
    Integer originalMcc,
    Boolean hold,
    Double amount,
    Double operationAmount,
    String currencyCode,
    Long commissionRate,
    Long cashbackAmount,
    Long balance,
    String comment,
    String receiptId,
    String invoiceId,
    String counterEdrpou,
    String counterIban,
    String counterName
) {
}

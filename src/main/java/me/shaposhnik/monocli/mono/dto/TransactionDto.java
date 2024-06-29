package me.shaposhnik.monocli.mono.dto;

public record TransactionDto(
    String id,
    Long time,
    String description,
    Integer mcc,
    Integer originalMcc,
    Boolean hold,
    Double amount,
    Double operationAmount,
    Integer currencyCode,
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

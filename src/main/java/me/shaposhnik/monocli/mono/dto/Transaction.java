package me.shaposhnik.monocli.mono.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import me.shaposhnik.monocli.mono.deserializer.Iso4217CurrencyDeserializer;
import me.shaposhnik.monocli.mono.deserializer.UnixEpochTimeDeserializer;

public record Transaction(
    String id,
    @JsonDeserialize(using = UnixEpochTimeDeserializer.class)
    LocalDateTime time,
    String description,
    Integer mcc,
    Integer originalMcc,
    Boolean hold,
    Double amount,
    Double operationAmount,
    @JsonDeserialize(using = Iso4217CurrencyDeserializer.class)
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

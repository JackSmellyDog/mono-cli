package me.shaposhnik.monocli.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import me.shaposhnik.monocli.integration.deserializer.Iso4217CurrencyDeserializer;
import me.shaposhnik.monocli.integration.deserializer.UnixEpochTimeDeserializer;

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

package me.shaposhnik.monocli.mono.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import me.shaposhnik.monocli.mono.deserializer.Iso4217CurrencyDeserializer;

public record Account(
    String id,
    String sendId,
    Long balance,
    Long creditLimit,
    String type,
    @JsonDeserialize(using = Iso4217CurrencyDeserializer.class)
    String currencyCode,
    String cashbackType,
    List<String> maskedPan,
    String iban
) {
}

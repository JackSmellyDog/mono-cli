package me.shaposhnik.monocli.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.shaposhnik.monocli.integration.deserializer.Iso4217CurrencyDeserializer;

public record Jar(
    String id,
    String sendId,
    String title,
    String description,
    @JsonDeserialize(using = Iso4217CurrencyDeserializer.class)
    String currencyCode,
    Long balance,
    Long goal
) {
}

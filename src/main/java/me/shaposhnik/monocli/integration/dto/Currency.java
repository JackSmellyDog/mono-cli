package me.shaposhnik.monocli.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import me.shaposhnik.monocli.integration.deserializer.Iso4217CurrencyDeserializer;
import me.shaposhnik.monocli.integration.deserializer.UnixEpochTimeDeserializer;

public record Currency(
    @JsonDeserialize(using = Iso4217CurrencyDeserializer.class)
    String currencyCodeA,
    @JsonDeserialize(using = Iso4217CurrencyDeserializer.class)
    String currencyCodeB,
    @JsonDeserialize(using = UnixEpochTimeDeserializer.class)
    LocalDateTime date,
    Double rateSell,
    Double rateBuy,
    Double rateCross
) {
}

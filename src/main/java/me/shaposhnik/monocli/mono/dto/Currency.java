package me.shaposhnik.monocli.mono.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import me.shaposhnik.monocli.mono.deserializer.Iso4217CurrencyDeserializer;
import me.shaposhnik.monocli.mono.deserializer.UnixEpochTimeDeserializer;

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

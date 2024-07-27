package me.shaposhnik.monocli.core;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Currency(
    String currencyCodeA,
    String currencyCodeB,
    LocalDateTime date,
    Double rateSell,
    Double rateBuy,
    Double rateCross
) {
}

package me.shaposhnik.monocli.core;

import java.time.LocalDateTime;

public record Currency(
    String currencyCodeA,
    String currencyCodeB,
    LocalDateTime date,
    Double rateSell,
    Double rateBuy,
    Double rateCross
) {
}

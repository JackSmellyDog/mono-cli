package me.shaposhnik.monocli.mono.dto;

public record CurrencyDto(
    Integer currencyCodeA,
    Integer currencyCodeB,
    Long date,
    Double rateSell,
    Double rateBuy,
    Double rateCross
) {
}

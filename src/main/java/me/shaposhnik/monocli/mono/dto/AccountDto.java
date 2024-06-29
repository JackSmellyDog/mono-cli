package me.shaposhnik.monocli.mono.dto;

import java.util.List;

public record AccountDto(
    String id,
    String sendId,
    Long balance,
    Long creditLimit,
    String type,
    Integer currencyCode,
    String cashbackType,
    List<String> maskedPan,
    String iban
) {
}

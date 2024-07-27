package me.shaposhnik.monocli.core;

import java.util.List;
import lombok.Builder;

@Builder
public record Account(
    String id,
    String sendId,
    Long balance,
    Long creditLimit,
    String type,
    String currencyCode,
    String cashbackType,
    List<String> maskedPan,
    String iban
) {
}

package me.shaposhnik.monocli.core;

import lombok.Builder;

@Builder
public record Jar(
    String id,
    String sendId,
    String title,
    String description,
    String currencyCode,
    Long balance,
    Long goal
) {
}

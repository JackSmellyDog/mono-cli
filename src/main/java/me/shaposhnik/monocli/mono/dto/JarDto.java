package me.shaposhnik.monocli.mono.dto;

public record JarDto(
    String id,
    String sendId,
    String title,
    String description,
    Integer currencyCode,
    Long balance,
    Long goal
) {
}

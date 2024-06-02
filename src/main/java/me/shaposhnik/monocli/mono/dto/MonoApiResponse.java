package me.shaposhnik.monocli.mono.dto;

public record MonoApiResponse<T>(String nativeBody, T body) {
}

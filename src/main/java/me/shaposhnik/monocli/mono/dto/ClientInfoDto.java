package me.shaposhnik.monocli.mono.dto;

import java.util.List;

public record ClientInfoDto(
    String clientId,
    String name,
    String webHookUrl,
    String permissions,
    List<AccountDto> accounts,
    List<JarDto> jars
) {
}

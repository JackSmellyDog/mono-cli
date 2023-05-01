package me.shaposhnik.monocli.integration.dto;

import java.util.List;

public record ClientInfo(
    String clientId,
    String name,
    String webHookUrl,
    String permissions,
    List<Account> accounts,
    List<Jar> jars
) {
}

package me.shaposhnik.monocli.core;

import java.util.List;
import lombok.Builder;

@Builder
public record ClientInfo(
    String clientId,
    String name,
    String webHookUrl,
    String permissions,
    List<Account> accounts,
    List<Jar> jars
) {
}

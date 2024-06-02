package me.shaposhnik.monocli.mono;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mono")
public record MonoProperties(String baseUrl,
                             String xToken,
                             Endpoints endpoints) {
  public record Endpoints(
      String currency,
      String clientInfo,
      String statement
  ) {
  }
}

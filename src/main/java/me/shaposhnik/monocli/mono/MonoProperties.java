package me.shaposhnik.monocli.mono;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mono")
record MonoProperties(String baseUrl,
                      String xToken,
                      int transactionLimit) {
}

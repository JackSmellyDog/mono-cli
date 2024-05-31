package me.shaposhnik.monocli.mono.client;

import me.shaposhnik.monocli.mono.MonoProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class ClientConfig {

  private final MonoProperties properties;

  public ClientConfig(MonoProperties properties) {
    this.properties = properties;
  }

  @Bean
  public WebClient publicWebClient() {
    return WebClient.builder()
        .baseUrl(properties.baseUrl())
        .build();
  }

  @Bean
  public WebClient privateWebClient() {
    return WebClient.builder()
        .baseUrl(properties.baseUrl())
        .defaultHeader("X-Token", properties.xToken())
        .build();
  }
  @Bean
  public MonoPublicClient monoPublicClient(@Qualifier("publicWebClient") WebClient client) {
    return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
        .build()
        .createClient(MonoPublicClient.class);
  }

  @Bean
  public MonoPrivateClient monoPrivateClient(@Qualifier("privateWebClient") WebClient client) {
    return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
        .build()
        .createClient(MonoPrivateClient.class);
  }

}

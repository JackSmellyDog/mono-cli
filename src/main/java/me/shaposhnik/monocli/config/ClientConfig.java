package me.shaposhnik.monocli.config;

import me.shaposhnik.monocli.integration.client.MonoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class ClientConfig {

  @Bean
  public HttpServiceProxyFactory httpServiceProxyFactory() {
    WebClient client = WebClient.builder()
        .baseUrl("https://api.monobank.ua/")
        .build();
    return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
  }

  @Bean
  public MonoClient monoClient(HttpServiceProxyFactory factory) {
    return factory.createClient(MonoClient.class);
  }

}

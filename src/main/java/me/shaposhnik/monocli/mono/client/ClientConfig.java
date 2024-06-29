package me.shaposhnik.monocli.mono.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import me.shaposhnik.monocli.mono.dto.ClientInfoDto;
import me.shaposhnik.monocli.mono.dto.CurrencyDto;
import me.shaposhnik.monocli.mono.dto.TransactionDto;
import me.shaposhnik.monocli.mono.exception.MonoApiException;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
@RegisterReflectionForBinding(classes = {
    CurrencyDto.class,
    TransactionDto.class,
    ClientInfoDto.class
})
class ClientConfig {
  @Bean
  public HttpServiceProxyFactory httpServiceProxyFactory() {
    var client = RestClient.builder()
        .defaultStatusHandler(HttpStatusCode::isError, (req, res) -> {
          var problemDetail = ProblemDetail.forStatusAndDetail(
              res.getStatusCode(),
              retrieveErrorBody(res.getBody())
          );
          throw new MonoApiException(problemDetail);
        })
        .build();
    return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
  }

  @Bean
  public MonoClient monoClient(HttpServiceProxyFactory factory) {
    return factory.createClient(MonoClient.class);
  }

  private String retrieveErrorBody(InputStream inputStream) {
    try {
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      return "Failed to retrieve the native error body";
    }
  }
}

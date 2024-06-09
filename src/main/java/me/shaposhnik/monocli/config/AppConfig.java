package me.shaposhnik.monocli.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration(proxyBeanMethods = false)
public class AppConfig {

  @Bean
  public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder()
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .failOnUnknownProperties(false)
        .featuresToDisable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .modules(new JavaTimeModule());
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    return builder.build();
  }

  @Bean
  public YAMLMapper yamlMapper() {
    return new YAMLMapper();
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

}

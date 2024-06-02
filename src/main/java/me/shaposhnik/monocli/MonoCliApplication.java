package me.shaposhnik.monocli;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MonoCliApplication {

  public static void main(String[] args) {
    // todo: to configuration
    new SpringApplicationBuilder(MonoCliApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}

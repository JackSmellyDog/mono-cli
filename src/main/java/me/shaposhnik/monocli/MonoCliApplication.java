package me.shaposhnik.monocli;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MonoCliApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MonoCliApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}

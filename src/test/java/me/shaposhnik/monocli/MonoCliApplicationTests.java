package me.shaposhnik.monocli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class MonoCliApplicationTests {

  @Test
  void contextLoads() {
    var modules = ApplicationModules.of(MonoCliApplication.class);
    modules.forEach(module -> System.out.println(
        "module: " + module.getName() + " : " + module.getBasePackage()));

    modules.verify();
  }

}

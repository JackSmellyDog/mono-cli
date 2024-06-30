package archtest;

import static com.tngtech.archunit.lang.conditions.ArchConditions.callMethod;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import me.shaposhnik.monocli.MonoCliApplication;

@AnalyzeClasses(packagesOf = MonoCliApplication.class)
class ArchitectureTest {

  @ArchTest
  void noLocalDateLocalDateTimeNowMethodAllowed(JavaClasses importedClasses) {
    ArchCondition<JavaClass> callingNowMethod = callMethod(LocalDateTime.class, "now")
        .or(callMethod(LocalDate.class, "now"));

    ArchRule rule = ArchRuleDefinition.noClasses()
        .should(callingNowMethod)
        .because("Each call should have java.time.Clock as an argument.");

    rule.check(importedClasses);
  }
}

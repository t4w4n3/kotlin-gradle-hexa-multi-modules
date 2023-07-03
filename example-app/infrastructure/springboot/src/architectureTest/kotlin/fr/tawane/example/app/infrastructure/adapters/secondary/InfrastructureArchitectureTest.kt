package fr.tawane.example.app.infrastructure.adapters.secondary

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions
import org.junit.jupiter.api.Test
import java.util.regex.Pattern
import kotlin.reflect.KVisibility.INTERNAL

const val gatewaysPackage = "fr.tawane.example.app.domain.gateways"
const val secondaryAdaptersPackage = "fr.tawane.example.app.infrastructure.adapters.secondary"

internal class InfrastructureArchitectureTest {

  @Test
  internal fun `all secondary adapters should implement gateways`() {
    val locationPattern = Pattern.compile(".*/build/classes/[^/]+/[^/]+[tT]est/.*")
    val importedClasses = ClassFileImporter()
      .withImportOption { location -> location.matches(locationPattern).not() }
      .importPackages(secondaryAdaptersPackage)

    val implementAGateway = object : ArchCondition<JavaClass>("Implement a gateway") {
      override fun check(javaClass: JavaClass, events: ConditionEvents) {
        val implementsAGateway: Boolean =
          javaClass.reflect().kotlin.visibility == INTERNAL || javaClass.interfaces.any { interfaze ->
            interfaze.name.startsWith(
              gatewaysPackage,
            )
          }
        Preconditions.checkState(implementsAGateway, javaClass)
      }
    }

    val rule: ArchRule = classes().should(implementAGateway)
    rule.check(importedClasses)
  }
}

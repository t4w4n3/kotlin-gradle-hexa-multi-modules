package fr.tawane.example.app.infrastructure.adapters.secondary

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.Location
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

const val secondaryPortsPackage = "fr.tawane.example.app.domain.ports.secondary"
const val secondaryAdaptersPackage = "fr.tawane.example.app.infrastructure.adapters.secondary"

internal class InfrastructureArchitectureTest {
  private val locationPattern = Pattern.compile(".*/build/classes/[^/]+/[^/]+[tT]est/.*")
  private val excludeTestClasses = { location: Location -> location.matches(locationPattern).not() }

  @Test
  internal fun `all secondary adapters should implement secondary ports`() {
    val importedClasses = ClassFileImporter()
      .withImportOption(excludeTestClasses)
      .importPackages(secondaryAdaptersPackage)

    val implementAGateway = object : ArchCondition<JavaClass>("Implement a secondary port") {
      override fun check(javaClass: JavaClass, events: ConditionEvents) {
        val implementsAGateway: Boolean =
          javaClass.isAnonymousClass || javaClass.hasAFrameworkAnnotation() || javaClass.implements(
            secondaryPortsPackage,
          )
        Preconditions.checkState(implementsAGateway, javaClass)
      }
    }

    val rule: ArchRule = classes().should(implementAGateway)
    rule.check(importedClasses)
  }

  private fun JavaClass.implements(interfacePackage: String) = interfaces.any { interfaze ->
    interfaze.name.startsWith(interfacePackage)
  }

  private fun JavaClass.hasAFrameworkAnnotation() = annotations.any { annotation ->
    annotation.type.name.startsWith("org.springframework") || annotation.type.name.startsWith("jakarta")
  }
}

package fr.tawane.example.app.domain

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions.checkState
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

private const val secondaryPortsPackage = "fr.tawane.example.app.domain.ports.secondary"
private const val secondaryAdaptersPackage = "fr.tawane.example.app.infrastructure.adapters.secondary"
private const val usecasesPackage = "fr.tawane.example.app.domain.usecases"
private val locationPattern: Pattern = Pattern.compile(".*/build/classes/[^/]+/[^/]+[tT]est/.*")

internal class ApplicationArchitectureTest {

  @Test
  internal fun `secondary ports should be implemented only by secondary adapters`() {
    val importedClasses = ClassFileImporter().withImportOption { location -> location.matches(locationPattern).not() }
      .importPackages("fr.tawane.example.app")
    val implementNoGateway = object : ArchCondition<JavaClass>("Implement no secondary port") {
      override fun check(javaClass: JavaClass, events: ConditionEvents?) {
        val implementsNoGateway: Boolean = javaClass.name.startsWith(secondaryAdaptersPackage) || javaClass.interfaces
          .none() { interfaze -> interfaze.name.startsWith(secondaryPortsPackage) }
        checkState(implementsNoGateway, javaClass)
      }
    }
    val rule: ArchRule = classes().that().resideOutsideOfPackage(secondaryPortsPackage).should(implementNoGateway)
    rule.check(importedClasses)
  }

  @Test
  internal fun `secondary ports should be used only by usecase`() {
    val importedClasses = ClassFileImporter().withImportOption { location -> location.matches(locationPattern).not() }
      .importPackages("fr.tawane.example.app")

    val useNoGateway = object : ArchCondition<JavaClass>("Use no secondary port") {
      override fun check(javaClass: JavaClass, events: ConditionEvents?) {
        val useNoGateway: Boolean =
          javaClass.allFields.none { field -> field.type.name.startsWith(secondaryPortsPackage) }
        val haveNoFieldWithGatewaysAsGeneric: Boolean = javaClass.allFields.none { field ->
          field.reflect().genericType.typeName.contains(secondaryPortsPackage)
        }
        checkState(useNoGateway && haveNoFieldWithGatewaysAsGeneric, javaClass)
      }
    }

    val rule: ArchRule = classes().that().resideOutsideOfPackage(usecasesPackage).should(useNoGateway)
    rule.check(importedClasses)
  }
}

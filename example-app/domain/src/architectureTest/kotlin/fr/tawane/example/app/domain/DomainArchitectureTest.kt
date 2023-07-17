package fr.tawane.example.app.domain

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

const val secondaryPortsPackage = "fr.tawane.example.app.domain.ports.secondary"

internal class DomainArchitectureTest {

  @Test
  internal fun `secondary ports should be interfaces`() {
    val importedClasses = ClassFileImporter().importPackages(secondaryPortsPackage)
    val rule: ArchRule = classes().that().resideInAPackage(secondaryPortsPackage)
      .should().beInterfaces()
    rule.check(importedClasses)
  }
}

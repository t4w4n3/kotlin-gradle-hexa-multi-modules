package fr.tawane.example.app.domain

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

const val gatewaysPackage = "fr.tawane.example.app.domain.gateways"

internal class DomainArchitectureTest {

  @Test
  internal fun `gateways should be interfaces`() {
    val importedClasses = ClassFileImporter().importPackages(gatewaysPackage)
    val rule: ArchRule = classes().that().resideInAPackage(gatewaysPackage)
      .should().beInterfaces()
    rule.check(importedClasses)
  }
}

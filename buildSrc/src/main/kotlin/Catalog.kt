import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.version(alias: String): String = this
  .extensions
  .getByType(VersionCatalogsExtension::class)
  .named("libs")
  .findVersion(alias)
  .orElseThrow { RuntimeException("lib not found in catalog : $alias") }
  .requiredVersion

fun Project.lib(name: String) = this
  .extensions
  .getByType(VersionCatalogsExtension::class)
  .named("libs")
  .findLibrary(name)
  .orElseThrow { RuntimeException("lib not found in catalog : $name") }

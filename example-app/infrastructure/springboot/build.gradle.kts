plugins {
  id("fr.tawane.kotlin-springboot-library-conventions")
  id("fr.tawane.kotlin-integrationTest-conventions")
  id("fr.tawane.kotlin-architectureTest-conventions")
  kotlin("plugin.jpa")
}

private val hypersistenceVersionRanges = listOf(
  6.2..6.2,
  6.0..6.1,
  5.5..5.6,
  5.2..5.4,
)

fun getVersion(version: String) = version
  .split(".")
  .take(2)
  .joinToString(".")
  .toDouble()
  .let { majorDotMinor ->
    hypersistenceVersionRanges
      .firstOrNull { range -> range.contains(majorDotMinor) }
      ?.start
      ?.toString()
      ?.replace(".", "")
      ?: "5"
  }

private val hibernateVersionCode = dependencyManagement
  .importedProperties["hibernate.version"]
  ?.let(::getVersion)
  ?: throw IllegalStateException("hibernate.version not found")

dependencies {
  api(libs.springbootStarterDataJpa)
  api("io.hypersistence:hypersistence-utils-hibernate-$hibernateVersionCode:3.5.1")
  // kotlin reflect
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation(project(":example-app:domain"))
  runtimeOnly(libs.h2) {
//    NOTE: the vendor states :
//    This is not a vulnerability of H2 Console ...
//    Passwords should never be passed on the command line and every qualified DBA or system administrator is expected to know that.
  }
  implementation(libs.liquibase)
}

plugins {
  id("fr.tawane.kotlin-springboot-library-conventions")
  id("fr.tawane.kotlin-integrationTest-conventions")
  id("fr.tawane.kotlin-architectureTest-conventions")
  kotlin("plugin.jpa")
}

dependencies {
  implementation(libs.springbootStarterDataJpa)
  implementation(project(":example-app:domain"))
  runtimeOnly(libs.h2) {
//    NOTE: the vendor states :
//    This is not a vulnerability of H2 Console ...
//    Passwords should never be passed on the command line and every qualified DBA or system administrator is expected to know that.
  }
  implementation(libs.liquibase)
}

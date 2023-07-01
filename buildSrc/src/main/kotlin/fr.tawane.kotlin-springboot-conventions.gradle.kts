import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("fr.tawane.kotlin-common-conventions")
  id("io.spring.dependency-management")
  id("org.springframework.boot")
  kotlin("plugin.spring")
}

dependencies {
  implementation(lib("springbootStarter"))
  testImplementation(lib("springbootStarterTest"))
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = version("javaTarget")
  }
}

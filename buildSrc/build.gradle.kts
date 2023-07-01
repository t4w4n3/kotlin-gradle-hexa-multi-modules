import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
}

dependencies {
  implementation(libs.kotlinGradlePlugin)
  implementation(libs.springbootGradlePlugin)
  implementation("org.jetbrains.kotlin:kotlin-allopen")
  implementation("org.jetbrains.kotlin:kotlin-noarg")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = libs.versions.javaTarget.get()
    allWarningsAsErrors = true
  }
}

java {
  sourceCompatibility = JavaVersion.toVersion(libs.versions.javaTarget.get())
  targetCompatibility = JavaVersion.toVersion(libs.versions.javaTarget.get())
}

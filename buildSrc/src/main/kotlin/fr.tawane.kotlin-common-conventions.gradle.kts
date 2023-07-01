import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

repositories {
  mavenCentral()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = version("javaTarget")
    allWarningsAsErrors = true
  }
}

java {
  sourceCompatibility = JavaVersion.toVersion(version("javaTarget"))
  targetCompatibility = JavaVersion.toVersion(version("javaTarget"))
}

dependencies {
  implementation(lib("kotlinLogging"))
  implementation(lib("logbackClassic"))
  testImplementation(platform(lib("junitBom")))
  testImplementation(lib("junit"))
  testImplementation(lib("assertj"))
}

tasks.named<Test>("test") {
  useJUnitPlatform()
  parallelJunitDynamic()
}

tasks.withType<Test>().configureEach {
  reports.html.required.set(false)
  if (!project.hasProperty("createReports")) {
    reports.junitXml.required.set(false)
  }
}

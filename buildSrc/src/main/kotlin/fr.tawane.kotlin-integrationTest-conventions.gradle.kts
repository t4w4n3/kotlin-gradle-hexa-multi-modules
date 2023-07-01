plugins {
  kotlin("jvm")
}

val integrationTest: SourceSet by sourceSets.creating {
  java.srcDir("src/integrationTest/kotlin")
}

configurations[integrationTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
configurations[integrationTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

val integrationTestTask = tasks.register<Test>("integrationTest") {
  description = "Runs integration tests."
  group = "verification"
  useJUnitPlatform()
  testClassesDirs = integrationTest.output.classesDirs
  classpath = integrationTest.runtimeClasspath
  shouldRunAfter(tasks.test)
  parallelJunitDynamic()
  reports.html.required.set(false)
  if (!project.hasProperty("createReports")) {
    reports.junitXml.required.set(false)
  }
}

tasks.check {
  dependsOn("integrationTest")
}

dependencies {
  "integrationTestImplementation"(project)
}

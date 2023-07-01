plugins {
  kotlin("jvm")
}

val architectureTest: SourceSet by sourceSets.creating {
  java.srcDir("src/architectureTest/kotlin")
}

configurations[architectureTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
configurations[architectureTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

val architectureTestTask = tasks.register<Test>("architectureTest") {
  description = "Runs architecture tests."
  group = "verification"
  useJUnitPlatform()
  testClassesDirs = architectureTest.output.classesDirs
  classpath = architectureTest.runtimeClasspath
  shouldRunAfter(tasks.test)
  parallelJunitDynamic()
  reports.html.required.set(false)
  if (!project.hasProperty("createReports")) {
    reports.junitXml.required.set(false)
  }
}

tasks.check {
  dependsOn("architectureTest")
}

dependencies {
  "architectureTestImplementation"(project)
  "architectureTestImplementation"(lib("archunit"))
  "architectureTestImplementation"("org.jetbrains.kotlin:kotlin-reflect")
}

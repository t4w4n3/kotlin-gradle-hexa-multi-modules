plugins {
  id("fr.tawane.kotlin-springboot-conventions")
  id("fr.tawane.kotlin-integrationTest-conventions")
  id("fr.tawane.kotlin-architectureTest-conventions")
  alias(libs.plugins.jib)
}

dependencies {
  implementation(libs.springbootStarterWeb)
  implementation(project(":example-app:domain"))
  implementation(project(":example-app:infrastructure"))
  implementation(project(":example-app:infrastructure:springboot"))
}

tasks.bootRun {
  systemProperty("spring.profiles.active", "dev")
  /* Because bootRun is not recommended for production use.
   It does not perform some of the steps that are necessary for a production-ready application, such as repackaging dependencies and optimizing jar files.*/
}

jib {
  from {
    image = "arm64v8/eclipse-temurin:${version("jdk")}-jre"
    platforms {
      platform {
        os = "linux"
        architecture = "arm64"
      }
    }
  }
}

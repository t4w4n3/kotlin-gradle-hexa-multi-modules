plugins {
  id("fr.tawane.kotlin-library-conventions")
  id("fr.tawane.kotlin-integrationTest-conventions")
  id("fr.tawane.kotlin-architectureTest-conventions")
  alias(libs.plugins.jib)
}

dependencies {
  implementation(project(":example-app:domain"))
  implementation(project(":example-app:infrastructure"))
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

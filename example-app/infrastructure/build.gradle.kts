plugins {
  id("fr.tawane.kotlin-library-conventions")
  id("fr.tawane.kotlin-integrationTest-conventions")
}

dependencies {
  implementation(project(":example-app:domain"))
}

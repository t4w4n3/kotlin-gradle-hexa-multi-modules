plugins {
  id("fr.tawane.kotlin-springboot-conventions")
  `java-library`
}

tasks.bootJar {
  enabled = false
}

tasks.bootRun {
  enabled = false
}

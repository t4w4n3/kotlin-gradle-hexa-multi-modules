plugins {
  kotlin("jvm")
  jacoco
}

tasks.check {
  finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
  dependsOn(tasks.test) // tests are required to run before generating the report
  reports {
    xml.required.set(false)
    csv.required.set(false)
    html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
  }
}

tasks.jacocoTestCoverageVerification {
  violationRules {
    rule {
      limit {
        minimum = "0.99".toBigDecimal()
      }
    }

    rule {
      isEnabled = false
      element = "CLASS"
      includes = listOf("org.gradle.*")

      limit {
        counter = "LINE"
        value = "TOTALCOUNT"
        maximum = "0.3".toBigDecimal()
      }
    }
  }
}

package fr.tawane.example.app.application.springboot

import fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa.JpaConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(scanBasePackages = ["fr.tawane.example.app"])
@Import(JpaConfiguration::class)
class App

fun main(args: Array<String>) {
  runApplication<App>(*args)
}

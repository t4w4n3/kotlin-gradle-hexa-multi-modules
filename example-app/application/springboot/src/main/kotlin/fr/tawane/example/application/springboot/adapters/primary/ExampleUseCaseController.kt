package fr.tawane.example.application.springboot.adapters.primary

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleUseCaseController {

  @GetMapping("/example")
  fun example(): String {
    return "Hello World"
  }
}

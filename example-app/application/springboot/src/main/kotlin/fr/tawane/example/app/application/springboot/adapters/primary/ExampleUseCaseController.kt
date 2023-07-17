package fr.tawane.example.app.application.springboot.adapters.primary

import fr.tawane.example.app.domain.model.DoSomethingQuery
import fr.tawane.example.app.domain.ports.primary.DoSomething
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DoSomethingController(
  private val useCase: DoSomething,
) {

  @GetMapping("/do/{id}")
  fun doSomething(@PathVariable id: String): String {
    useCase.accept(DoSomethingQuery(id))
    return "Done"
  }
}

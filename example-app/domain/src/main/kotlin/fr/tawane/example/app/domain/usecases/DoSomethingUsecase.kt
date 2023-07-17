package fr.tawane.example.app.domain.usecases

import fr.tawane.example.app.domain.model.DoSomethingQuery
import fr.tawane.example.app.domain.ports.primary.DoSomething
import fr.tawane.example.app.domain.ports.secondary.ForbiddenService
import fr.tawane.example.app.domain.ports.secondary.ThingRepository

class DoSomethingUsecase(
  private val thingRepository: ThingRepository,
  private val forbiddenService: ForbiddenService,
) : DoSomething {
  override fun accept(doSomethingQuery: DoSomethingQuery) {
    thingRepository.read(doSomethingQuery.thingId)?.let { thing ->
      if (thing.nameContainsForbiddenWord()) forbiddenService.notify(thing)
    }
  }
}

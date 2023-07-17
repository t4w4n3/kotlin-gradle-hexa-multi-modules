package fr.tawane.example.domain.usecases

import fr.tawane.example.app.domain.model.DoSomethingQuery
import fr.tawane.example.app.domain.model.Thing
import fr.tawane.example.app.domain.ports.secondary.ForbiddenService
import fr.tawane.example.app.domain.ports.secondary.ThingRepository
import fr.tawane.example.app.domain.usecases.DoSomethingUsecase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DoSomethingTest {

  private val thingRepository = object : ThingRepository {
    val memory = listOf(
      Thing("id1", "name"),
      Thing("id2", "forbidden name"),
    ).associateBy { it.id }

    override fun read(thingId: String): Thing? = memory[thingId]
  }
  private val forbiddenService = object : ForbiddenService {
    val calls = mutableListOf<Thing>()
    override fun notify(thing: Thing) {
      calls.add(thing)
    }
  }
  private val sut = DoSomethingUsecase(thingRepository, forbiddenService)

  @Test
  internal fun `should not notify forbiddenService that something has been done to a thing without forbidden name`() {
    // Given
    val doSomethingQuery = DoSomethingQuery("id1")

    // When
    sut.accept(doSomethingQuery)

    // Then
    assertThat(forbiddenService.calls).hasSize(0)
  }

  @Test
  internal fun `should notify forbiddenService that something has been done to a thing with forbidden name`() {
    // Given
    val doSomethingQuery = DoSomethingQuery("id2")

    // When
    sut.accept(doSomethingQuery)

    // Then
    assertThat(forbiddenService.calls)
      .hasSize(1)
      .first().usingRecursiveComparison().isEqualTo(Thing("id2", "forbidden name"))
  }
}

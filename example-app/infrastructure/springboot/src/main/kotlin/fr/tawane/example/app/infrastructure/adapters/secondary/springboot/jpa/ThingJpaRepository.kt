package fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa

import fr.tawane.example.app.domain.model.Thing
import fr.tawane.example.app.domain.ports.secondary.ThingRepository
import io.hypersistence.utils.spring.repository.BaseJpaRepository

internal class ThingJpaRepository(private val thingJpaRepository: BaseJpaRepository<ThingEntity, String>) :
  ThingRepository {
  override fun read(thingId: String): Thing = thingJpaRepository.findById(thingId)
    .map { Thing(it.id, it.name) }
    .orElse(Thing.notFound)
}

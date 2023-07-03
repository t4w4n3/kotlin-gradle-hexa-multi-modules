package fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
internal data class ThingEntity(
  @Id
  val id: String,
  val name: String,
)

package fr.tawane.example.app.domain.ports.secondary

import fr.tawane.example.app.domain.model.Thing

fun interface ThingRepository {
  fun read(thingId: String): Thing?
}

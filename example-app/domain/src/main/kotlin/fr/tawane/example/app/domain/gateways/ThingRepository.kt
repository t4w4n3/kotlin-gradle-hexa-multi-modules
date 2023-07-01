package fr.tawane.example.app.domain.gateways

import fr.tawane.example.app.domain.model.Thing

fun interface ThingRepository {
  fun read(thingId: String): Thing?
}

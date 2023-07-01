package fr.tawane.example.app.domain.gateways

import fr.tawane.example.app.domain.model.Thing

fun interface ForbiddenService {
  fun notify(thing: Thing)
}

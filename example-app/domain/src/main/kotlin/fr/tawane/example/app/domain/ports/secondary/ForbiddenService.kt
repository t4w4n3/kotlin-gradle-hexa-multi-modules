package fr.tawane.example.app.domain.ports.secondary

import fr.tawane.example.app.domain.model.Thing

fun interface ForbiddenService {
  fun notify(thing: Thing)
}

package fr.tawane.example.app.domain.model

open class Thing(
  val id: String,
  private val name: String,
) {

  companion object {
    val notFound = NotFoundThing()
  }

  fun nameContainsForbiddenWord(): Boolean {
    return name.words().any { it.isForbidden() }
  }
}

class NotFoundThing : Thing(
  id = "not-found",
  name = "not-found",
)

private fun String.words(): List<String> = split(" ")

private fun String.isForbidden(): Boolean = forbiddenWords.any { it == this }

private val forbiddenWords = listOf("forbidden")

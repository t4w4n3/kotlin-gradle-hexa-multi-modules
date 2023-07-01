package fr.tawane.example.app.domain.model

class Thing(
  val id: String,
  private val name: String,
) {
  fun nameContainsForbiddenWord(): Boolean {
    return name.words().any { it.isForbidden() }
  }
}

private fun String.words(): List<String> = split(" ")

private fun String.isForbidden(): Boolean = forbiddenWords.any { it == this }

private val forbiddenWords = listOf("forbidden")

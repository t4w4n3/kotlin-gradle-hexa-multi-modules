package fr.tawane.example.app.domain.ports.primary

import fr.tawane.example.app.domain.model.DoSomethingQuery
import java.util.function.Consumer

fun interface DoSomething : Consumer<DoSomethingQuery>

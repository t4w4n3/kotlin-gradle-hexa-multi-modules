package fr.tawane.example.app.domain.ports

import fr.tawane.example.app.domain.model.DoSomethingQuery
import java.util.function.Consumer

interface DoSomething : Consumer<DoSomethingQuery>

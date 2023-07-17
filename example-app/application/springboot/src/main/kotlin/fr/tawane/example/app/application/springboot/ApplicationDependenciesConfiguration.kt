package fr.tawane.example.app.application.springboot

import fr.tawane.example.app.domain.ports.primary.DoSomething
import fr.tawane.example.app.domain.ports.secondary.ForbiddenService
import fr.tawane.example.app.domain.ports.secondary.ThingRepository
import fr.tawane.example.app.domain.usecases.DoSomethingUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationDependenciesConfiguration {

  @Bean
  fun forbiddenService(): ForbiddenService = ForbiddenService { println("send data to an external api") }

  @Bean
  fun doSomething(forbiddenService: ForbiddenService, thingRepository: ThingRepository): DoSomething =
    DoSomethingUsecase(thingRepository, forbiddenService)
}

package fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa

import fr.tawane.example.app.domain.ports.secondary.ThingRepository
import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(
  basePackages = ["fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa"],
  repositoryBaseClass = BaseJpaRepositoryImpl::class,
)
@Configuration
@EntityScan("fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa")
class JpaConfiguration {

  @Bean
  internal fun thingRepository(thingBaseJpaRepository: ThingBaseJpaRepository): ThingRepository =
    ThingJpaRepository(thingBaseJpaRepository)
}

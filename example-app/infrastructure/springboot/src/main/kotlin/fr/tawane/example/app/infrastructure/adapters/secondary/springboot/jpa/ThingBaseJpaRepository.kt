package fr.tawane.example.app.infrastructure.adapters.secondary.springboot.jpa

import io.hypersistence.utils.spring.repository.BaseJpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface ThingBaseJpaRepository : BaseJpaRepository<ThingEntity, String>

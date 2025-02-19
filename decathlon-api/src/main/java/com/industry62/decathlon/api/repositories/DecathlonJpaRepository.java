package com.industry62.decathlon.api.repositories;

import com.industry62.decathlon.api.repositories.entities.EventEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecathlonJpaRepository extends JpaRepository<EventEntity, Long> {

  Optional<EventEntity> findByEventType(String eventType);

}

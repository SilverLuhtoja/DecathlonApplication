package com.industry62.decathlon.api.repositories;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.industry62.decathlon.api.mappers.EventMapper;
import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.repositories.entities.EventEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
@RequiredArgsConstructor
public class DecathlonRepository {

  private final DecathlonJpaRepository decathlonJpaRepository;
  private final EventMapper mapper;

  public List<Event> getRecords() {
    List<EventEntity> entities = decathlonJpaRepository.findAll();
    return entities.stream().map(mapper::toDomain).toList();
  }

  public Event createOrUpdateRecord(Event domain) {
    return decathlonJpaRepository.findByEventType(domain.eventType().name())
        .map(existingEntity -> updateEntity(existingEntity, domain))
        .orElseGet(() -> createNewEntity(domain));
  }

  public void deleteRecord(String eventName) {
    EventEntity entity = decathlonJpaRepository.findByEventType(eventName)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Event not found"));
    decathlonJpaRepository.delete(entity);
  }

  private Event updateEntity(EventEntity existingEntity, Event domain) {
    existingEntity.setPoints(domain.points());
    return mapper.toDomain(decathlonJpaRepository.save(existingEntity));
  }

  private Event createNewEntity(Event domain) {
    return mapper.toDomain(decathlonJpaRepository.save(mapper.toEntity(domain)));
  }
}

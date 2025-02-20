package com.industry62.decathlon.api.repositories;

import static com.industry62.decathlon.api.models.enums.DecathlonEventType.SHOT_PUT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.repositories.entities.EventEntity;
import com.industry62.decathlon.config.IntegrationTest;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DecathlonRepositoryTest extends IntegrationTest {

  @Autowired
  private DecathlonRepository repository;

  @Autowired
  private DecathlonJpaRepository decathlonJpaRepository;

  @Override
  protected String[] getTableNames() {
    return new String[] { "decathlon.events" };
  }

  @Nested
  class GetRecords {

    @Test
    @Sql("/testdata/create-events.sql")
    void shouldReturnEvents() {
      List<Event> events = repository.getRecords();

      assertEquals(1, events.size());
    }
  }

  @Nested
  class CreateOrUpdateRecord {

    @Test
    void shouldCreateEvent() {
      Event event = new Event(SHOT_PUT, 99);

      repository.createOrUpdateRecord(event);

      EventEntity entity = decathlonJpaRepository.findAll().get(0);
      assertEquals(SHOT_PUT.name(), entity.getEventType());
      assertEquals(99, entity.getPoints());
    }

    @Test
    void shouldUpdateEvent() {
      Event event = new Event(SHOT_PUT, 99);
      repository.createOrUpdateRecord(event);

      List<EventEntity> entities = decathlonJpaRepository.findAll();
      assertEquals(1, entities.size());
      assertEquals(99, entities.get(0).getPoints());

      Event updateEvent = new Event(SHOT_PUT, 123);
      repository.createOrUpdateRecord(updateEvent);

      entities = decathlonJpaRepository.findAll();
      assertEquals(1, entities.size());
      assertEquals(123, entities.get(0).getPoints());
    }
  }

  @Nested
  class DeleteRecord {

    @Test
    @Sql("/testdata/create-events.sql")
    void shouldDeleteEvent() {
      List<EventEntity> entities = decathlonJpaRepository.findAll();

      assertEquals(1, entities.size());

      repository.deleteRecord("SHOT_PUT");

      entities = decathlonJpaRepository.findAll();
      assertEquals(0, entities.size());
    }
  }
}

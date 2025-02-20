package com.industry62.decathlon.api.services;

import static com.industry62.decathlon.api.datasets.DecathlonDataset.EVENT;
import static com.industry62.decathlon.api.models.enums.DecathlonEventType.DISCUS_THROW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.repositories.DecathlonRepository;
import com.industry62.decathlon.api.exceptions.InvalidValueException;
import com.industry62.decathlon.config.UnitTest;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DecatlhonServiceTest extends UnitTest {

  @Mock
  private DecathlonRepository repository;

  @InjectMocks
  private DecathlonService service;

  @Nested
  class GetEvents {

    @Test
    void shouldReturnEvents() {
      when(repository.getRecords()).thenReturn(List.of(EVENT));

      List<Event> result = service.getEvents();

      assertEquals(1, result.size());
    }
  }

  @Nested
  class CreateOrUpdateEvents {

    @ParameterizedTest
    @CsvSource({ "-1", "1400" })
    void shouldThrowWhenPointsNotValid(int points) {
      Event event = new Event(DISCUS_THROW, points);

      InvalidValueException exception = assertThrows(InvalidValueException.class,
          () -> service.createOrUpdateEvent(event));

      String expectedMessage = String.format("Invalid points: %d. Allowed values are between 0 and 1370.",
          points);
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  @Nested
  class DeleteEvents {

    @Test
    void shouldReturnEvents() {
      String eventName = "SHOT_PUT";

      service.deleteEvent(eventName);

      verify(repository).deleteRecord(eventName);
      ;
    }
  }
}

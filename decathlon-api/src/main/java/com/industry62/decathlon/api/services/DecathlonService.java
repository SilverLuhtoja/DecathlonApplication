package com.industry62.decathlon.api.services;

import com.industry62.decathlon.api.exceptions.InvalidValueException;
import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.models.enums.DecathlonEventType;
import com.industry62.decathlon.api.repositories.DecathlonRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecathlonService {

  private static final String INVALID_POINTS_MESSAGE = "Invalid points: %d. Allowed values are between 0 and %d.";

  private final DecathlonRepository repository;

  public List<Event> getEvents() {
    return repository.getRecords();
  }

  public Event createOrUpdateEvent(Event event) {
    validate(event);
    return repository.createOrUpdateRecord(event);
  }

  public void deleteEvent(String eventName) {
    repository.deleteRecord(eventName);
  }

  private void validate(Event event) {
    int maxScore = DecathlonEventType.valueOf(event.eventType().name()).getMaxScore();

    if (event.points() > maxScore || event.points() < 0) {
      throw new InvalidValueException(String.format(INVALID_POINTS_MESSAGE, event.points(), maxScore));
    }
  }
}

package com.industry62.decathlon.api.mappers;

import com.industry62.decathlon.api.controllers.resources.EventResource;
import com.industry62.decathlon.api.exceptions.InvalidValueException;
import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.models.enums.DecathlonEventType;
import com.industry62.decathlon.api.repositories.entities.EventEntity;
import com.industry62.decathlon.config.MapStructConfig;
import java.util.Arrays;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface EventMapper {

  String WRONG_EVENT_VALUE_MESSAGE = "Invalid event name: %s. Allowed values: %s";

  @Mapping(source = "eventName", target = "eventType", qualifiedByName = "mapEventType")
  Event toDomain(EventResource resource);

  Event toDomain(EventEntity entity);

  EventEntity toEntity(Event domain);

  @Named("mapEventType")
  default DecathlonEventType mapEventType(String name) {
    return Arrays.stream(DecathlonEventType.values())
        .filter(event -> event.name().equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(() -> new InvalidValueException(
            String.format(WRONG_EVENT_VALUE_MESSAGE, name, DecathlonEventType.getAllowedEventValues())));
  }
}

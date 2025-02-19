package com.industry62.decathlon.api.controllers;

import com.industry62.decathlon.api.controllers.resources.EventResource;
import com.industry62.decathlon.api.mappers.EventMapper;
import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.services.DecathlonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DecathlonController {

  private final DecathlonService service;
  private final EventMapper mapper;

  // @GetMapping("/total")
  // public ResponseEntity<Integer> getTotalPoints() {
  // return ResponseEntity.ok(service.getTotalPoints());
  // }

  @GetMapping
  public List<Event> getEvents() {
    return service.getEvents();
  }

  @PostMapping
  @Description("Creates or updates specific sport field, based on field existance")
  public ResponseEntity<Event> createOrUpdateEvent(@RequestBody EventResource resource) {
    Event event = mapper.toDomain(resource);
    return ResponseEntity.ok(service.createOrUpdateEvent(event));
  }

  @DeleteMapping("/delete/{eventName}")
  public ResponseEntity<String> deleteEvent(@PathVariable String eventName) {
    service.deleteEvent(eventName);
    return ResponseEntity.ok("Event :" + eventName + " has been deleted.");
  }
}

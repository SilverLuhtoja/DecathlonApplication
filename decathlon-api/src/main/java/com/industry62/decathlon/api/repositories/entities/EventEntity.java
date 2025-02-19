package com.industry62.decathlon.api.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "events", schema = "decathlon")
public class EventEntity extends BaseEntity {

  @Column(name = "event_type", nullable = false, unique = true)
  private String eventType;

  @Column(nullable = false)
  private int points;
}

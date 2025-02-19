package com.industry62.decathlon.api.models;

import com.industry62.decathlon.api.models.enums.DecathlonEventType;

public record Event(DecathlonEventType eventType, int points) {

}

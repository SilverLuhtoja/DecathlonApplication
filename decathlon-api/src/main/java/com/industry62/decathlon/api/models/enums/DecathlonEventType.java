package com.industry62.decathlon.api.models.enums;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DecathlonEventType {
  HUNDRED_METERS("100 meters", 1202),
  LONG_JUMP("Long jump", 1312),
  SHOT_PUT("Shot put", 1291),
  HIGH_JUMP("High jump", 1250),
  FOUR_HUNDRED_METERS("400 meters", 1188),
  HUNDRED_TEN_METERS_HURDLES("110 meters hurdles", 1185),
  DISCUS_THROW("Discus throw", 1370),
  POLE_VAULT("Pole vault", 1257),
  JAVELIN_THROW("Javelin throw", 1360),
  FIFTEEN_HUNDRED_METERS("1500 meters", 1296);

  private final String displayName;
  private final int maxScore;

  public static String getAllowedEventValues() {
    return Arrays.stream(DecathlonEventType.values())
        .map(Enum::name)
        .collect(Collectors.joining(", "));
  }
}

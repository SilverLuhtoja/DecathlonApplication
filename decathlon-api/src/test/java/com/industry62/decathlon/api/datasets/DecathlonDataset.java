package com.industry62.decathlon.api.datasets;

import com.industry62.decathlon.api.models.Event;
import com.industry62.decathlon.api.models.enums.DecathlonEventType;

public class DecathlonDataset {

  public static final String EVENT_NAME = "SHOT_PUT";
  public static final String API_BASE_PATH = "/api";
  public static final String DELETE_ENDPOINT = API_BASE_PATH + "/delete/" + EVENT_NAME;
  public static final Event EVENT = new Event(DecathlonEventType.SHOT_PUT, 10);
}

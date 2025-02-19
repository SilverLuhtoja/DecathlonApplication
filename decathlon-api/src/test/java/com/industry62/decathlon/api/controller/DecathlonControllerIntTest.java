package com.industry62.decathlon.api.controller;

import static com.industry62.decathlon.api.datasets.DecathlonDataset.API_BASE_PATH;
import static com.industry62.decathlon.api.datasets.DecathlonDataset.DELETE_ENDPOINT;
import static com.industry62.decathlon.api.datasets.DecathlonDataset.EVENT_NAME;
import static com.industry62.decathlon.api.models.enums.DecathlonEventType.HUNDRED_METERS;
import static com.industry62.decathlon.api.models.enums.DecathlonEventType.SHOT_PUT;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.industry62.decathlon.api.repositories.DecathlonJpaRepository;
import com.industry62.decathlon.api.repositories.entities.EventEntity;
import com.industry62.decathlon.config.IntegrationTest;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

class DecathlonControllerIntTest extends IntegrationTest {

  @Autowired
  private DecathlonJpaRepository jpaRepository;

  @Nested
  class CreateOrUpdateEvents {

    @Test
    void createEvent() throws Exception {
      mockMvc.perform(post(API_BASE_PATH)
              .contentType("application/json")
              .content("{\"eventName\":\"HUNDRED_METERS\", \"points\":1000}"))
          .andExpect(status().isOk());

      List<EventEntity> entities = jpaRepository.findAll();
      assertEquals(1, entities.size());
      assertEquals(HUNDRED_METERS.name(), entities.get(0).getEventType());
      assertEquals(1000, entities.get(0).getPoints());
    }
  }

  @Nested
  class GetEvents {

    @Test
    @Sql("/testdata/create-events.sql")
    void getEvents() throws Exception {
      mockMvc.perform(get(API_BASE_PATH))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].eventType", is(SHOT_PUT.name())))
          .andExpect(jsonPath("$[0].points", is(1)));
    }
  }

  @Nested
  class DeleteEvents {

    @Test
    @Sql("/testdata/create-events.sql")
    void shouldDeleteEvent() throws Exception {
      String expectedResponseMessage = "Event :" + EVENT_NAME + " has been deleted.";
      mockMvc.perform(delete(DELETE_ENDPOINT))
          .andExpect(status().isOk())
          .andExpect(content().string(expectedResponseMessage));
    }

    @Test
    void shouldThrowNotFound() throws Exception {
      mockMvc.perform(delete(DELETE_ENDPOINT))
          .andExpect(status().isNotFound());
    }
  }

  @Override
  protected String[] getTableNames() {
    return new String[]{"decathlon.events"};
  }
}

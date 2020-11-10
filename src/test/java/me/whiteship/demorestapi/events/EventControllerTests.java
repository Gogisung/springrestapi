package me.whiteship.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
//        Event event = EventDto.builder()
//                .name("String")
//                .description("test11")
//                .beginEnrollmentDateTime(LocalDateTime.of(2020,11,23,14,21,22))
//                .closeEnrollmentDateTime(LocalDateTime.of(2020,11,24,14,22,22))
//                .beginEventDateTime(LocalDateTime.of(2020,11,25,14,22,22))
//                .endEventDateTime(LocalDateTime.of(2020,11,26,14,22,22))
//                .basePrice(100)
//                .maxPrice(200)
//                .limitOfEnrollment(100)
//                .location("강남역 D2").build();
//                .free(true)
//                .offline(false)
//                .eventStatus(EventStatus.PUBLISHED)
//                .build();

//        mockMvc.perform(post("/api/events/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaTypes.HAL_JSON)
//                .content(objectMapper.writeValueAsString(event)))
//            .andDo(print())
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("id").exists())
//            .andExpect(header().exists(HttpHeaders.LOCATION))
//            .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
//            .andExpect(jsonPath("id").value(Matchers.not(10)))
//            .andExpect(jsonPath("free").value(Matchers.not(true)))
//            .andExpect(jsonPath("free").value(EventStatus.DRAFT))
//        ;
    }

    @Test
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                .id(100)
                .name("String")
                .description("test11")
                .beginEnrollmentDateTime(LocalDateTime.of(2020,11,23,14,21,22))
                .closeEnrollmentDateTime(LocalDateTime.of(2020,11,24,14,22,22))
                .beginEventDateTime(LocalDateTime.of(2020,11,25,14,22,22))
                .endEventDateTime(LocalDateTime.of(2020,11,26,14,22,22))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void createEvent_Bad_Request_Empty_Input() throws Exception {
        EventDto eventDto = EventDto.builder().build();
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createEvent_Bad_Request_Wrong_Input() throws Exception {
        EventDto eventDto = EventDto.builder().build();
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest());
    }

}

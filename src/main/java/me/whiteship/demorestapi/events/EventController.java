package me.whiteship.demorestapi.events;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/events")
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Event event = modelMapper.map(eventDto, Event.class);
        Event save = this.eventRepository.save(event);
        URI uri = linkTo(EventController.class).slash(save.getId()).toUri();
        event.setId(10);
        return ResponseEntity.created(uri).body(event);


    }
}

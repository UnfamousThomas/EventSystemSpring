package com.practice.event.EventSystem.event;

import com.practice.event.EventSystem.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping(path = "{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping(path = "/names/{name}")
    public List<Event> getEventsByName(@PathVariable String name) {
        return eventService.getEventsByName(name);
    }

    @PostMapping
    public void addEvent(@RequestBody Event event) {
        eventService.registerEvent(event);
    }

    @PutMapping(path = "/addParticipants/{eventId}")
    public void addParticipants(@PathVariable Long eventId, @RequestParam Long userId) {
        eventService.addParticipants(eventId, userId);
    }

    @PutMapping(path = "{eventId}")
    public void updateEvent(@PathVariable Long eventId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) LocalDateTime start,
                            @RequestParam(required = false) Integer hours) {
        eventService.updateEvent(eventId, name, description, start, hours);

    }

    @DeleteMapping(path = "{eventId}")
    public void deleteEventById(@PathVariable Long eventId) {
        eventService.deleteEventById(eventId);
    }
}

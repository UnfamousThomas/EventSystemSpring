package com.practice.event.EventSystem.event;

import com.practice.event.EventSystem.models.Event;
import com.practice.event.EventSystem.models.User;
import com.practice.event.EventSystem.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    @Autowired
    public EventService(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(optionalEvent.isEmpty()) throw new IllegalStateException("No event with id: " + eventId + " found");
        return optionalEvent.get();
    }

    public void registerEvent(Event event) {
        eventRepository.save(event);
    }

    public void addParticipants(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User user = userService.getUserById(userId);

        event.getParticipants().add(user);

        eventRepository.save(event);
    }


    public void updateEvent(Long eventId, String name, String description, LocalDateTime start, Integer hours) {
        Event event = getEventById(eventId);
        if(name != null) event.setName(name);
        if(description != null) event.setDescription(description);
        if(start != null) event.setStartTime(start);
        if(hours != null && hours != 0) event.setHours(hours);

        eventRepository.save(event);
    }

    public List<Event> getEventsByName(String name) {
        return eventRepository.findByName(name);
    }

    public void deleteEventById(Long eventId) {
        Event event = getEventById(eventId);
        eventRepository.delete(event);
    }
}

package com.example.application.controllers;

import com.example.application.data.Event;
import com.example.application.services.EventService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<Event> getEvents() {
        return service.getAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.saveEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}

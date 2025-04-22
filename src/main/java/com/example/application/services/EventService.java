package com.example.application.services;

import com.example.application.data.Event;
import com.example.application.data.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;

        Event e1 = new Event();
        e1.setName("Tangomarkkinat");
        e1.setDate("22.1.2022");
        repository.save(e1);

        Event e2 = new Event();
        e2.setName("Kalaryssäys");
        e2.setDate("22.1.2025");
        repository.save(e2);

        Event e3 = new Event();
        e3.setName("Mansikkakarnevaalit");
        e3.setDate("22.1.2025");
        repository.save(e3);
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event saveEvent(Event event) {
        return repository.save(event);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
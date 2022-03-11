package org.daypilot.demo.html5eventcalendarspring.controller;

import org.daypilot.demo.html5eventcalendarspring.domain.Event;
import org.daypilot.demo.html5eventcalendarspring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EventController {


    @Autowired
    EventRepository er;

    @GetMapping("/list/events")
    public ResponseEntity<Object> getAllEvents() {
        Iterable<Event> events = er.findAll();
        events.forEach((element) -> {
            if (element.getStart().equals(LocalDateTime.now())) {
                System.out.println("Today is the event");
            }
            System.out.println(element.getText());
        });
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}

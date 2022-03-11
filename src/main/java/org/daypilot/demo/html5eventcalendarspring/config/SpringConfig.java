package org.daypilot.demo.html5eventcalendarspring.config;

import org.daypilot.demo.html5eventcalendarspring.domain.Event;
import org.daypilot.demo.html5eventcalendarspring.repository.EventRepository;
import org.daypilot.demo.html5eventcalendarspring.util.EmailNotification;
import org.daypilot.demo.html5eventcalendarspring.util.SmsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableScheduling
public class SpringConfig {
    @Autowired
    EventRepository er;

    @Autowired
    SmsUtility smsUtility;

    @Autowired
    EmailNotification emailNotification;

    @Scheduled(fixedDelay = 28800000)
    public void scheduleFixedDelayTask() {

        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 28800000);

        LocalDate toLocalDate = LocalDateTime.now().toLocalDate();
        String str = toLocalDate.toString() + " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime todaysDateTime = LocalDateTime.parse(str, formatter);

        List<Event> events = er.findAllByStartEquals(todaysDateTime);
        List<String> messages = new ArrayList<>();
        String[] arr = new String[events.size()];

        if (events.size() > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = events.get(i).getText();
                System.out.println("Array value: " + arr[i]);
            }

            for (int i = 0; i < arr.length; i++) {
                if (i > 0) {
                    if (arr[i].equalsIgnoreCase(arr[i - 1])) {
                        continue;
                    } else {
                        messages.add(arr[i]);
                    }
                } else {
                    messages.add(arr[i]);
                }
            }
            System.out.println("Messages List value: " + messages.toString());
            emailNotification.sendMail("Zakaria1337@gmail.com", messages.toString());
        }
    }

    public Iterable<Event> getAllEvents() {
        Iterable<Event> events = er.findAll();
        events.forEach((element) -> {
            if (element.getStart().equals(LocalDateTime.now())) {
                System.out.println("Today is the event");
            }
            System.out.println(element.getText());
        });
        return events;
    }
}
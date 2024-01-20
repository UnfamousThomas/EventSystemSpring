package com.practice.event.EventSystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private long id;
    private String name;
    private String description;
    @ManyToOne private User organizer;
    @ManyToMany
    private List<User> participants;
    private LocalDateTime startTime;
    private int hours;

    public LocalDateTime getEndingTime() {
        return startTime.plusHours(hours);
    }
}

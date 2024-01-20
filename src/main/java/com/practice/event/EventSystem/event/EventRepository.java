package com.practice.event.EventSystem.event;

import com.practice.event.EventSystem.models.Event;
import com.practice.event.EventSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e from Event e where e.name= ?1")
    List<Event> findByName(String name);
}

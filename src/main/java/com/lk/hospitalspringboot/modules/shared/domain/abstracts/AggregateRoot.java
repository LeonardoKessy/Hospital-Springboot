package com.lk.hospitalspringboot.modules.shared.domain.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<Object> events = new ArrayList<>();

   public List<Object> pullEvents() {
        List<Object> events = new ArrayList<>(this.events);
        this.events.clear();
        return events;
   }

   protected void registerEvent(Object event) {
       this.events.add(event);
   }
}

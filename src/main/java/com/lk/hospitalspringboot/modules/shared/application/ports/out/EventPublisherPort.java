package com.lk.hospitalspringboot.modules.shared.application.ports.out;

import com.lk.hospitalspringboot.modules.shared.domain.events.DomainEvent;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}

package com.lk.hospitalspringboot.modules.shared.infrastructure.adapters.out.events;

import com.lk.hospitalspringboot.modules.shared.application.ports.out.EventPublisherPort;
import com.lk.hospitalspringboot.modules.shared.domain.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisherAdapter implements EventPublisherPort {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}

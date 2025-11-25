package com.shoposphere.shipping.service.impl;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shoposphere.exception.ProductNotAvailableException;
import com.shoposphere.order.entity.OutboxEvent;
import com.shoposphere.order.repository.OutboxRepository;

@Component
public class OutboxJmsPublisher {

    private final OutboxRepository outboxRepository;
    private final JmsTemplate jmsTemplate;

    public OutboxJmsPublisher(OutboxRepository outboxRepository, JmsTemplate jmsTemplate) {
        this.outboxRepository = outboxRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedDelayString = "${outbox.poll.delay.ms:2000}")
    @Transactional
    public void publishPending() {
        List<OutboxEvent> events = outboxRepository.findTop100ByPublishedFalseOrderByCreatedAtAsc();
        for (OutboxEvent e : events) {
            try {
            	
                jmsTemplate.convertAndSend("shipping.events", e.getPayload());
                e.markPublished();
                outboxRepository.save(e);
            } catch (Exception ex) {
                throw new ProductNotAvailableException(ex.getMessage());
            }
        }
    }
}

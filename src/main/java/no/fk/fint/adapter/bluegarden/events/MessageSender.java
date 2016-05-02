package no.fk.fint.adapter.bluegarden.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.fk.event.Event;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String replyTo, Event event) {
        try {
            String json = new ObjectMapper().writeValueAsString(event);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setCorrelationId(event.getId().getBytes());

            Message message = new Message(json.getBytes(), messageProperties);
            rabbitTemplate.send(replyTo, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

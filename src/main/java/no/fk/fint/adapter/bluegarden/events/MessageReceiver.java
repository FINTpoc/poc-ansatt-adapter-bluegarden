package no.fk.fint.adapter.bluegarden.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.fk.Ansatt;
import no.fk.event.Event;
import no.fk.event.Type;
import no.fk.fint.adapter.bluegarden.Bluegarden;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class MessageReceiver {

    public static final String QUEUE_IN = "fint:hfk.no:employee:in";

    @Autowired
    private Bluegarden bluegarden;

    @Autowired
    private MessageSender messageSender;

    @RabbitListener(queues = MessageReceiver.QUEUE_IN)
    public void receive(Message message) throws IOException {
        Event event = new ObjectMapper().readValue(new String(message.getBody()), Event.class);
        log.info("Received message: {}", event);

        Events eventType = Events.get(event.getVerb());
        if (eventType == Events.GET_EMPLOYEES) {
            List<Ansatt> employees = bluegarden.getEmployees();

            Event<Ansatt> responseEvent = new Event<>("hfk.no", Events.GET_EMPLOYEES.verb(), Type.RESPONSE);
            responseEvent.setId(event.getId());
            responseEvent.setData(employees);
            messageSender.send(message.getMessageProperties().getReplyTo(), responseEvent);
        }
    }
}

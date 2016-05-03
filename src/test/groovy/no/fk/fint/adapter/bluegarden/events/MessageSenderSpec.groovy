package no.fk.fint.adapter.bluegarden.events

import no.fk.event.Event
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import spock.lang.Specification

class MessageSenderSpec extends Specification {

    private RabbitTemplate rabbitTemplate
    private MessageSender messageSender

    void setup() {
        rabbitTemplate = Mock(RabbitTemplate)
        messageSender = new MessageSender(rabbitTemplate: rabbitTemplate)
    }

    def "Send message"() {
        when:
        messageSender.send("reply-to", new Event())

        then:
        1 * rabbitTemplate.send(_ as String, _ as Message)
    }
}

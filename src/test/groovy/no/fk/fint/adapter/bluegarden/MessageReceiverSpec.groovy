package no.fk.fint.adapter.bluegarden

import com.fasterxml.jackson.databind.ObjectMapper
import no.fk.Ansatt
import no.fk.event.Event
import no.fk.event.Type
import no.fk.fint.adapter.bluegarden.events.Events
import no.fk.fint.adapter.bluegarden.events.MessageReceiver
import no.fk.fint.adapter.bluegarden.events.MessageSender
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import spock.lang.Specification

class MessageReceiverSpec extends Specification {

    private Bluegarden bluegarden
    private MessageSender messageSender
    private MessageReceiver messageReceiver

    void setup() {
        bluegarden = Mock(Bluegarden)
        messageSender = Mock(MessageSender)

        messageReceiver = new MessageReceiver(bluegarden: bluegarden, messageSender: messageSender)
    }

    def "Receive event"() {
        given:
        MessageProperties messageProperties = new MessageProperties()
        messageProperties.setReplyTo("reply_to")

        Event event = new Event("hfk.no", Events.GET_EMPLOYEES.verb(), Type.REQUEST)
        def content = new ObjectMapper().writeValueAsBytes(event)
        Message message = new Message(content, messageProperties)

        when:
        messageReceiver.receive(message)

        then:
        1 * bluegarden.getEmployees() >> [new Ansatt()]
        1 * messageSender.send(_ as String, _ as Event)
        noExceptionThrown()
    }
}

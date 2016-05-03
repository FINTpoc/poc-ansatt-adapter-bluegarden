package no.fk.fint.adapter.bluegarden

import com.fasterxml.jackson.databind.ObjectMapper
import no.fk.event.Event
import no.fk.event.Type
import no.fk.fint.adapter.bluegarden.events.Events
import no.fk.fint.adapter.bluegarden.events.MessageReceiver
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.IgnoreIf
import spock.lang.Specification

@IgnoreIf({ !Boolean.valueOf(properties["local.rabbitmq"]) })
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("dev")
class BluegardenAdapterIntegrationSpec extends Specification {

    @Autowired
    private RabbitTemplate rabbitTemplate

    def "Get employees"() {
        given:
        Event request = new Event("hfk.no", Events.GET_EMPLOYEES.verb(), Type.REQUEST)
        def messageContent = new ObjectMapper().writeValueAsBytes(request)
        MessageProperties messageProperties = new MessageProperties()
        messageProperties.setCorrelationId(request.getId().getBytes())

        when:
        Message response = rabbitTemplate.sendAndReceive(MessageReceiver.QUEUE_IN, new Message(messageContent, messageProperties))
        def event = new ObjectMapper().readValue(response.getBody(), Event)

        then:
        event.getData().size() == 5
    }
}

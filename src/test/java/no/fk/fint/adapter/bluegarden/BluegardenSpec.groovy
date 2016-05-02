package no.fk.fint.adapter.bluegarden

import spock.lang.Specification

class BluegardenSpec extends Specification {

    private Bluegarden bluegarden

    void setup() {
        bluegarden = new Bluegarden()
        bluegarden.init()
    }

    def "Get employees"() {
        when:
        def employees = bluegarden.getEmployees()

        then:
        employees.size() == 5
    }
}

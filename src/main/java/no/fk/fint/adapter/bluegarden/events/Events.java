package no.fk.fint.adapter.bluegarden.events;

public enum Events {
    GET_EMPLOYEES("getEmployees"),
    GET_EMPLOYEE("getEmployee"),
    UPDATE_EMPLOYEE("updateEmployee");

    private final String verb;

    Events(String verb) {
        this.verb = verb;
    }

    public String verb() {
        return verb;
    }

    public static Events get(String verb) {
        for (Events events : Events.values()) {
            if(events.verb().equals(verb))
                return events;
        }
        return null;
    }
}

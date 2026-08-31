package jimbo.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String data, String from, String to) {
        super(data);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
    }

    @Override
    public String serialise() {
        return "E|" + super.serialise() + "|" + from + "|" + to;
    }
}

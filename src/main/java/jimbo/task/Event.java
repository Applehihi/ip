package jimbo.tasks;

import jimbo.JimboException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String data, String from, String to) throws JimboException {
        super(data);
        try {
            this.from = LocalDateTime.parse(from, INPUT_DATE_TIME_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JimboException("invalid date/time, must be in format yyyy-MM-dd HHmm"
                + "\n" + "example: 2026-01-01 1300 for Jan 1 2026 01:00pm");
        }
        if (this.from.isAfter(this.to)) {
            throw new JimboException("time travel is not allowed, sorry :(" + "\n"
                    + "make sure your from date/time is before your to date/time");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from.format(FRIENDLY_DATE_TIME_FORMAT)
                + " to " + to.format(FRIENDLY_DATE_TIME_FORMAT) + ")";
    }

    @Override
    public String serialise() {
        return "E|" + super.serialise() + "|" + from.format(INPUT_DATE_TIME_FORMAT) + "|"
                + to.format(INPUT_DATE_TIME_FORMAT);
    }
}

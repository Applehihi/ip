package jimbo.tasks;

import jimbo.JimboException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String data, String by) throws JimboException {
        super(data);
        try {
            this.by = LocalDateTime.parse(by, INPUT_DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JimboException("invalid date/time, must be in format yyyy-MM-dd HHmm"
                    + "\n" + "example: 2026-01-01 1300 for Jan 1 2026 01:00pm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by.format(FRIENDLY_DATE_TIME_FORMAT) + ")";
    }

    @Override
    public String serialise() {
        return "D|" + super.serialise() + "|" + by.format(INPUT_DATE_TIME_FORMAT);
    }
}

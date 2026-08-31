package jimbo.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String data, String by) {
        super(data);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by + ")";
    }

    @Override
    public String serialise() {
        return "D|" + super.serialise() + "|" + by;
    }
}

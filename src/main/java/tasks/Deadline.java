package tasks;

public class Deadline extends Task {
    private String by;
    public Deadline(String data, String by) {
        super(data);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by + ")";
    }
}

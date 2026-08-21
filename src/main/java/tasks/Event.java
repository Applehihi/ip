package tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String data, String from, String to) {
        super(data);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
    }
}

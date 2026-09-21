package jimbo.task;

import jimbo.JimboException;

import java.time.format.DateTimeFormatter;

/**
 * The base abstract task class.
 */
public abstract class Task {
    protected static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter FRIENDLY_DATE_TIME_FORMAT = DateTimeFormatter
            .ofPattern("MMM dd yyyy hh:mma");
    protected static final String INVALID_DATE_TIME_MESSAGE = "invalid date/time, must be in format yyyy-MM-dd HHmm"
            + "\n" + "example: 2026-01-01 1300 for Jan 1 2026 01:00pm";


    private String data;
    private boolean isDone;

    public Task(String data) {
        this.data = data;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    private String formatCheckmark() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        return formatCheckmark() + " " + data;
    }

    public String serialise() {
        if (isDone) {
            return 1 + "|" + data;
        }
        return 0 + "|" + data;
    }

    public static Task deserialise(String data) throws JimboException {
        String[] params = data.split("\\|");
        Task task;
        switch (params[0]) {
            case "T":
                task = new Todo(params[2]);
                break;
            case "D":
                task = new Deadline(params[2], params[3]);
                break;
            case "E":
                task = new Event(params[2], params[3], params[4]);
                break;
            default:
                throw new JimboException("this task type seems invalid");
        }
        if (params[1].equals("1")) {
            task.mark();
        }
        return task;
    }

    public String getData() {
        return data;
    }
}

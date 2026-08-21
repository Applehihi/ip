public class Task {
    private String data;
    private boolean isDone;

    Task(String data) {
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
}

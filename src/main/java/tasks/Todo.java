package tasks;

public class Todo extends Task {
    public Todo(String data) {
        super(data);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

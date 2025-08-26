import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

/**
 * Adds a task to the list and shows a confirmation message.
 */
    public void addTask(Task t, Ui ui) {
        this.tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n  " + t
                + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
    * Deletes a task from the list by index and shows a confirmation message.
    */
    public void deleteTask(int index, Ui ui) throws SaajidException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new SaajidException("Task number " + (index+1) + " does not exist.");
        }
        Task removed = this.tasks.remove(index);
        ui.showMessage("Noted. I've removed this task:\n  " + removed
                + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
    * Displays all tasks in the list.
    */
    public void listTasks(Ui ui) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append((i+1)).append(".").append(this.tasks.get(i)).append("\n");
        }
        ui.showMessage(sb.toString().trim());
    }

    /* retrieve a particular task at a particular index */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    //function to check the size of the arraylist
    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

package saajid.task;

import saajid.exception.InvalidCommandException;
import saajid.ui.Ui;

import java.util.ArrayList;

import java.time.LocalDateTime;

/*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
     */

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /** Constructs an empty task list. */
    public TaskList() {}

    /** Constructs a TaskList initialized with existing tasks. */
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
    public void deleteTask(int index, Ui ui) throws InvalidCommandException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidCommandException("Task number " + (index+1) + " does not exist.");
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
    /**
     * Finds and displays all tasks that contain the given keyword.
     */
    public void findTasks(String keyword, Ui ui) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                sb.append(count).append(".").append(t).append("\n");
            }
        }
        if (count == 0) {
            ui.showMessage("No matching tasks found for keyword: " + keyword);
        } else {
            ui.showMessage(sb.toString().trim());
        }
    }

    /**
     * Reschedules the specified Event task with new start and end times.
     *
     * @param ui the UI to display updates
     * @param task the Event task to reschedule
     * @param newFrom the new starting date and time
     * @param newTo the new ending date and time
     */
    public void rescheduleTask(Ui ui, Event task, LocalDateTime newFrom, LocalDateTime newTo) {
        Event event = task;
        event.setFrom(newFrom);
        event.setTo(newTo);
        ui.showMessage("Got it! Event rescheduled:\n" + event);
    }

    /**
     * Reschedules the specified Deadline task with a new deadline.
     *
     * @param ui the UI to display updates
     * @param task the Deadline task to reschedule
     * @param newBy the new deadline date and time
     */
    public void rescheduleTask(Ui ui, Deadline task, LocalDateTime newBy) {
        Deadline deadline = task;
        deadline.setBy(newBy);
        ui.showMessage("Got it! Deadline rescheduled:\n" + deadline);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }
    /** Returns the number of tasks. */
    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

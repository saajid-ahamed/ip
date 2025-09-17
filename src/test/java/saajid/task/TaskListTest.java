package saajid.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saajid.exception.InvalidCommandException;
import saajid.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TaskListTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
    }

    @Test
    void testAddTask() {
        Task todo = new Todo("Read book");
        taskList.addTask(todo, ui);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void testDeleteTask_validIndex() throws InvalidCommandException {
        Task t1 = new Todo("Task 1");
        taskList.addTask(t1, ui);
        Task t2 = new Todo("Task 2");
        taskList.addTask(t2, ui);

        taskList.deleteTask(0, ui); // remove first
        assertEquals(1, taskList.size());
        assertEquals(t2, taskList.getTask(0));
    }

    @Test
    void testDeleteTask_invalidIndex() {
        assertThrows(InvalidCommandException.class, () -> taskList.deleteTask(0, ui));
    }

    @Test
    void testListTasks_empty() {
        taskList.listTasks(ui);
        assertEquals(0, taskList.size());
    }

    @Test
    void testListTasks_nonEmpty() {
        Task todo = new Todo("Task 1");
        taskList.addTask(todo, ui);
        taskList.listTasks(ui);
        assertEquals(1, taskList.size());
    }

    @Test
    void testGetTask_validIndex() {
        Task t = new Todo("Read book");
        taskList.addTask(t, ui);
        assertEquals(t, taskList.getTask(0));
    }

    @Test
    void testSizeAndGetTasks() {
        Task t1 = new Todo("Task 1");
        Task t2 = new Todo("Task 2");
        taskList.addTask(t1, ui);
        taskList.addTask(t2, ui);

        assertEquals(2, taskList.size());
        ArrayList<Task> tasks = taskList.getTasks();
        assertTrue(tasks.contains(t1));
        assertTrue(tasks.contains(t2));
    }
}

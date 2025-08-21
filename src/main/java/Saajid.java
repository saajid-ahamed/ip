import java.util.Scanner;

public class Saajid {
    private static final String NAME = "Saajid";
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[Saajid.MAX_TASKS];
    private int taskCount = 0;
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    public void greeting() {
        System.out.println(Saajid.HORIZONTAL_LINE);
        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", Saajid.NAME));
        System.out.println("\n" + Saajid.HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(Saajid.HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you soon!");
        System.out.println("\n" + Saajid.HORIZONTAL_LINE);
    }

    public void echo(String input) {
        System.out.println(Saajid.HORIZONTAL_LINE);
        System.out.println(input);
        System.out.println("\n" + Saajid.HORIZONTAL_LINE);
    }

    private void addTask(Task t) {
        this.tasks[this.taskCount] = t;
        this.taskCount++;
        System.out.println(Saajid.HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        System.out.println("\n" + Saajid.HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
       Saajid saajid = new Saajid();
       saajid.greeting();
       Scanner sc = new Scanner(System.in);
       while (true) {
           String input = sc.nextLine().trim();
           if (input.equalsIgnoreCase("bye")) {
               saajid.exit();
               break;
           } else if (input.equalsIgnoreCase("list")) {
               System.out.println(Saajid.HORIZONTAL_LINE);
               System.out.println("Here are the tasks in your list:");
               for (int i = 1; i <= saajid.taskCount; i++) {
                   System.out.println(i + "." + saajid.tasks[i - 1]);
               }
               System.out.println("\n" + Saajid.HORIZONTAL_LINE);
           } else if (input.startsWith("mark")) {
               int index = Integer.parseInt(input.substring(5)) - 1;
               if (index >= 0 && index < saajid.taskCount) {
                   saajid.tasks[index].markAsDone();
                   System.out.println(Saajid.HORIZONTAL_LINE);
                   System.out.println("Nice! I've marked this task as done:");
                   System.out.println("  " + saajid.tasks[index]);
                   System.out.println("\n" + Saajid.HORIZONTAL_LINE);
               }
           } else if (input.startsWith("unmark")) {
               int index = Integer.parseInt(input.substring(7)) - 1;
               if (index >= 0 && index < saajid.taskCount) {
                   saajid.tasks[index].markAsNotDone();
                   System.out.println(Saajid.HORIZONTAL_LINE);
                   System.out.println("OK, I've marked this task as not done yet:");
                   System.out.println("  " + saajid.tasks[index]);
                   System.out.println("\n" + Saajid.HORIZONTAL_LINE);
               }
           } else if (input.startsWith("todo ")) {
            String desc = input.substring(5);
            Task task = new Todo(desc);
            saajid.addTask(task);
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            Task task = new Deadline(parts[0], parts[1]);
            saajid.addTask(task);
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            Task task = new Event(parts[0], parts[1], parts[2]);
            saajid.addTask(task);
        }
       }

    }
}

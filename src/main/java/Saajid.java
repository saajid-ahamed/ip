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
           try {
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
                   if (input.length() <= 5) {
                       throw new SaajidException("Please provide a task number!!");
                   }
                   int index = Integer.parseInt(input.substring(5)) - 1;
                   if (index >= 0 && index < saajid.taskCount) {
                       saajid.tasks[index].markAsDone();
                       System.out.println(Saajid.HORIZONTAL_LINE);
                       System.out.println("Nice! I've marked this task as done:");
                       System.out.println("  " + saajid.tasks[index]);
                       System.out.println("\n" + Saajid.HORIZONTAL_LINE);
                   } else {
                       throw new SaajidException("Task number " + (index+1) + " does not exist.");
                   }
               } else if (input.startsWith("unmark")) {
                   if (input.length() <= 7) {
                       throw new SaajidException("Please provide a task number!!");
                   }
                   int index = Integer.parseInt(input.substring(7)) - 1;
                   if (index >= 0 && index < saajid.taskCount) {
                       saajid.tasks[index].markAsNotDone();
                       System.out.println(Saajid.HORIZONTAL_LINE);
                       System.out.println("OK, I've marked this task as not done yet:");
                       System.out.println("  " + saajid.tasks[index]);
                       System.out.println("\n" + Saajid.HORIZONTAL_LINE);
                   } else {
                       throw new SaajidException("Task number " + (index+1) + " does not exist.");
                   }
               } else if (input.startsWith("todo")) {
                   if (input.length() <= 5) {
                       throw new SaajidException("The description of a todo cannot be empty.");
                   }
                   String desc = input.substring(5);
                   Task task = new Todo(desc);
                   saajid.addTask(task);
               } else if (input.startsWith("deadline")) {
                   if (input.length() <= 9) {
                       throw new SaajidException("The deadline command must include a description and a /by time.");
                   }
                   String[] parts = input.substring(9).split(" /by ", 2);
                   if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
                       throw new SaajidException("The deadline command must include a description and a /by time.");
                   }
                   Task task = new Deadline(parts[0], parts[1]);
                   saajid.addTask(task);
               } else if (input.startsWith("event")) {
                   if (input.length() <= 6) {
                       throw new SaajidException("The event command must include a description, /from, and /to times.");
                   }
                   String[] parts = input.substring(6).split(" /from | /to ");
                   if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
                       throw new SaajidException("The event command must include a description, /from, and /to times.");
                   }
                   Task task = new Event(parts[0], parts[1], parts[2]);
                   saajid.addTask(task);
               } else {
                   throw new SaajidException("I AM SORRY BUT I DO NOT UNDERSTAND WHAT THAT MEANS!");
               }
           } catch (SaajidException e) {
               System.out.println(Saajid.HORIZONTAL_LINE);
               System.out.println(e.getMessage());
               System.out.println(Saajid.HORIZONTAL_LINE);
           }
       }

    }
}

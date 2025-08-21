import java.util.Scanner;

public class Saajid {
    private static final String NAME = "Saajid";
    private static final int MAX_TASKS = 100;
    private String[] tasks = new String[Saajid.MAX_TASKS];
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
               for (int i = 1; i <= saajid.taskCount; i++) {
                   System.out.println(i + ". " + saajid.tasks[i - 1]);
               }
               System.out.println("\n" + Saajid.HORIZONTAL_LINE);
           } else {
               saajid.tasks[saajid.taskCount] = input;
               saajid.taskCount++;
               System.out.println(Saajid.HORIZONTAL_LINE);
               System.out.println("added: " + input);
               System.out.println("\n" + Saajid.HORIZONTAL_LINE);
           }
       }
    }
}

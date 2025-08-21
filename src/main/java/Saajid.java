public class Saajid {
    private static final String NAME = "Saajid";
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
    public static void main(String[] args) {
       Saajid saajid = new Saajid();
       saajid.greeting();
       saajid.exit();
    }
}

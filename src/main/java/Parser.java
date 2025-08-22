public class Parser {

    public Command parse(String input) throws SaajidException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        if (commandWord.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (commandWord.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (commandWord.equalsIgnoreCase("delete")) {
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to delete!");
            }
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("mark")) {
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to mark!");
            }
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("unmark")) {
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to unmark!");
            }
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("todo")) {
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SaajidException("The todo command must include a description.");
            }
            return new AddCommand(new Todo(words[1].trim()));
        } else if (commandWord.equalsIgnoreCase("deadline")) {
            if (words.length < 2 || !words[1].contains("/by")) {
                throw new SaajidException("The deadline command must include a description and a /by time.");
            }
            String[] deadlineParts = words[1].split("/by", 2);
            if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new SaajidException("The deadline command must include a description and a /by time.");
            }
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        } else if (commandWord.equalsIgnoreCase("event")) {
            if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
                throw new SaajidException("The event command must include a description, /from, and /to times.");
            }
            String[] eventParts = words[1].split("/from", 2);
            if (eventParts[0].trim().isEmpty()) {
                throw new SaajidException("The event command must include a description, /from, and /to times.");
            }
            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new SaajidException("The event command must include a description, /from, and /to times.");
            }
            return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        } else {
            throw new SaajidException("I AM SORRY BUT I DO NOT UNDERSTAND WHAT THAT MEANS!");
        }
    }
}

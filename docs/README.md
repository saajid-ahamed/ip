# Saajid Chatbot

Saajid is a lightweight chatbot that acts as your personal task manager directly from the command line.
With simple text-based commands, you can create and organize tasks, set deadlines, schedule events,
and stay on top of your to-do list.
It keeps your tasks neatly stored, allows quick searching and rescheduling, and gives clear feedback to help you stay
productive and organized.
---

## Table of Contents
- [Features](#features)
- [Commands](#commands)
- [Getting Started](#getting-started)
- [Example Usage](#example-usage)

---

## Features

- ✨ **Stay Organized**  
  Add, delete, and view your tasks anytime with simple commands.

- 🔍 **Find What You Need**  
  Search for tasks quickly using keywords in their descriptions.

- 📝 **Flexible Task Types**
    - **ToDo:** A simple reminder for things you need to get done.
    - **Deadline:** Tasks with a specific due date and time.
    - **Event:** Tasks with a start and end time for proper scheduling.

- ✅ **Track Progress**  
  Mark tasks as done when completed, or unmark them if needed.

- 📅 **Change Plans Easily**  
  Reschedule existing Deadlines or Events to new dates/times.

- 🖥 **User-Friendly Output**  
  Clean, structured, and easy-to-read responses.

- 💾 **Automatic Saving**  
  All tasks are stored automatically so you can continue where you left off.


---

## Commands

### Add a ToDo
```todo <description>```

**Example:**  
```todo Read book```

### Add a Deadline
```deadline <description> /by <yyyy-mm-dd HHmm>```


**Example:**  
```deadline Submit report /by 2025-09-20 2359```



### Add an Event
```event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>```


**Example:**  
```event Concert /from 2025-09-21 1400 /to 2025-09-21 1600```



### List all Tasks
```list```



### Mark a Task as Done
```mark <task index>```


**Example:**  
```mark 1```



### Unmark a Task
```unmark <task index>```

**Example:**  
```unmark 1```

### Delete a Task
```delete <task index>```

**Example:**  
```delete 2```

### Find Tasks by Description
```find <keyword>```

**Example:**  
```find book```

### Reschedule a Deadline
```reschedule <task index> /by <yyyy-mm-dd HHmm>```

**Example:**  
```reschedule 2 /by 2025-09-25 1800```

### Reschedule an Event
```reschedule <task index> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>```

**Example:**  
```reschedule 3 /from 2025-09-21 1500 /to 2025-09-21 1700```

### Exit the Program
```bye```

---

## Getting Started

### Quick Start
1. Download the latest `.jar` version from the GitHub repository.
2. Open a command terminal and navigate to the directory containing the `.jar` file.
3. Run:  
   ```java -jar saajid.jar```
4. Type commands in the command box.

---

## Example Usage
```
Hello! I'm Saajid
What can I do for you?

todo shower

Got it. I've added this task:
[T][ ] shower
Now you have 1 task in the list.

deadline assignment /by 2025-12-12 2359

Got it. I've added this task:
[D][ ] assignment (by: Dec 12 2025 2359)
Now you have 2 tasks in the list.

event Tea party /from 2025-12-12 1300 /to 2025-12-12 1800

Got it. I've added this task:
[E][ ] Tea party (from: Dec 12 2025 1300 to: Dec 12 2025 1800)
Now you have 3 tasks in the list.
```
---

Enjoy managing your tasks with **Saajid**! 🎯  
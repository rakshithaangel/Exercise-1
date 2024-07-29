import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Singleton class
class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        if (isTaskOverlapping(task)) {
            System.out.println("Error: Task conflicts with existing task.");
            return;
        }
        tasks.add(task);
        System.out.println("Task added successfully. No conflicts.");
        notifyObservers();
    }

    public void removeTask(Task task) {
        if (tasks.remove(task)) {
            System.out.println("Task removed successfully.");
            notifyObservers();
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private boolean isTaskOverlapping(Task newTask) {
        for (Task task : tasks) {
            if (task.getStartTime().isBefore(newTask.getEndTime()) && newTask.getStartTime().isBefore(task.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    private void notifyObservers() {
        // Notify observers of task updates
    }
}

// Factory class
class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        return new Task(description, start, end, priority);
    }
}

// Task class
class Task implements Comparable<Task> {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Task other) {
        return this.startTime.compareTo(other.startTime);
    }

    @Override
    public String toString() {
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ": " + description + " [" + priority + "]";
    }
}

public class AstronautScheduleOrganizer {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:mm): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:mm): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority level: ");
                    String priority = scanner.nextLine();

                    try {
                        Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                        scheduleManager.addTask(task);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter task description to remove: ");
                    String taskDescription = scanner.nextLine();
                    Task taskToRemove = null;
                    for (Task task : scheduleManager.getTasks()) {
                        if (task.toString().contains(taskDescription)) {
                            taskToRemove = task;
                            break;
                        }
                    }
                    if (taskToRemove != null) {
                        scheduleManager.removeTask(taskToRemove);
                    } else {
                        System.out.println("Error: Task not found.");
                    }
                    break;

                case 3:
                    List<Task> tasks = scheduleManager.getTasks();
                    Collections.sort(tasks);
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks scheduled for the day.");
                    } else {
                        System.out.println("Tasks sorted by start time:");
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }
}
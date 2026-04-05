import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    
    public static void saveTasks(ArrayList<String> tasks) {
        try {
            FileWriter writer = new FileWriter("tasks.txt");

            for (String task : tasks) {
                writer.write(task + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }   

    public static void loadTasks(ArrayList<String> tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("No previous data found");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        loadTasks(tasks);

        while(true){
            System.out.println("\n----------------------");
            System.out.println("To-Do-List Application");
            System.out.println("----------------------");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.println("6. About Application");
            System.out.println("----------------------");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1){
                System.out.print("Enter the task: ");
                String task = sc.nextLine();
                if (tasks.contains(task)){
                    System.out.println("Task already exists...! Please Try again.");
                } else {
                    tasks.add(task);
                    System.out.println("Task added successfully...!✅");
                    saveTasks(tasks);
                }
            }else if (choice == 2){
                if (tasks.isEmpty()){
                    System.out.println("No Tasks Available...!");
                } else {
                    System.out.println("\nYour tasks...");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                System.out.println("Here are your tasks...");     
            }else if (choice == 3){
                if (tasks.isEmpty()){
                    System.out.println("No Tasks Available...!");
                } else {
                    System.out.print("Enter the task number to Delete: ");
                    int taskNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Are you sure? (yes/no)");
                    String confirmation = sc.nextLine();
                    if (confirmation.equalsIgnoreCase("yes")){
                        if (taskNumber > 0 && taskNumber <= tasks.size()){
                            String removedTask = tasks.remove(taskNumber - 1);
                            System.out.println("Task '" + removedTask + "' Task deleted successfully 🚀");
                            saveTasks(tasks);
                        } else {
                            System.out.println("Invalid task number...! Please Try Again.");
                        }
                    } else {
                        System.out.println("Task deletion cancelled.");
                    }
                }
            }else if (choice == 4){
                if (tasks.isEmpty()){
                    System.out.println("No Tasks Available...!");
                } else {
                    System.out.print("Enter the task number to update: ");
                    int taskNumber = sc.nextInt();
                    sc.nextLine();
                    if (taskNumber > 0 && taskNumber <= tasks.size()){
                        System.out.print("Enter the new task: ");
                        String newTask = sc.nextLine();
                        if (tasks.contains(newTask)){
                            System.out.println("Task already exists...! Please Try again.");
                        } else {
                            tasks.set(taskNumber - 1, newTask);
                            System.out.println("Task updated successfully...!✅");
                            saveTasks(tasks);
                        }
                    } else {
                        System.out.println("Invalid task number...! Please Try Again.");
                    }
                }          
            }else if (choice == 5){
                System.out.println("Exiting the application...!");
                System.exit(0);
            }else if (choice == 6){
                System.out.println("This is a simple To-Do-List application developed in Java. --By SAGAR-- ");
            }else {
                System.out.println("Invalid choice....! please Try Again.");
            }
        }
    }
}
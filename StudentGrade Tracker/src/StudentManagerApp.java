import java.io.IOException;
import java.util.Scanner;

/**
 * The main application class for managing student records and grades.
 *
 * This console-based application provides a menu-driven interface to:
 * - Add, remove, and search for students
 * - Add or update course grades
 * - Generate reports and save/load student data to/from a file
 *
 * Dependencies:
 * - StudentManager: Handles core logic and data storage
 * - InputeHelper: Manages validated user input
 *
 * File persistence is handled through serialization.
 */
public class StudentManagerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    /**
     * Main entry point of the application. Loads data and presents menu options.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        loadData(); // Attempt to load saved data on startup

        while (true) {
            displayMainMenu();
            int choice = getMenuChoice(1, 8);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> updateCourse();
                case 4 -> addCourseToStudent();
                case 5 -> searchStudent();
                case 6 -> generateReport();
                case 7 -> saveData();
                case 8 -> {
                    saveData(); // Auto-save on exit
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMainMenu() {
        System.out.println("\n===== STUDENT GRADE TRACKER =====");
        System.out.println("1. Add New Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Update Course Grade");
        System.out.println("4. Add Course to Student");
        System.out.println("5. Search Student");
        System.out.println("6. Generate Class Report");
        System.out.println("7. Save Data");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");
    }

    /**
     * Gets a valid menu choice from the user within a given range.
     *
     * @param min Minimum valid option
     * @param max Maximum valid option
     * @return The user's choice as an integer
     */
    private static int getMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Please enter a number between %d and %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Prompts the user for student information and adds a new student.
     */
    private static void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        String id = InputeHelper.getString("Enter student ID: ");
        String firstName = InputeHelper.getString("Enter first name: ");
        String lastName = InputeHelper.getString("Enter last name: ");

        Student student = new Student(firstName, lastName, id);
        manager.addStudent(student);
    }

    /**
     * Removes a student from the system by ID.
     */
    private static void removeStudent() {
        System.out.println("\n--- REMOVE STUDENT ---");
        String id = InputeHelper.getString("Enter student ID to remove: ");
        if (manager.removeStudent(id)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    /**
     * Updates the grade of an existing course for a student.
     */
    private static void updateCourse() {
        System.out.println("\n--- UPDATE COURSE GRADE ---");
        String studentId = InputeHelper.getString("Enter student ID: ");
        String courseId = InputeHelper.getString("Enter course ID to update: ");
        manager.updateCourse(studentId, courseId);
    }

    /**
     * Adds a new course to a student's record.
     */
    private static void addCourseToStudent() {
        System.out.println("\n--- ADD COURSE TO STUDENT ---");
        String studentId = InputeHelper.getString("Enter student ID: ");
        manager.addCourse(studentId);
    }

    /**
     * Searches for a student by ID and displays their record.
     */
    private static void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        String id = InputeHelper.getString("Enter student ID: ");
        manager.searchStudent(id);
    }

    /**
     * Generates and displays a class-wide report of all students.
     */
    private static void generateReport() {
        manager.studentReport();
    }

    /**
     * Saves current student data to file.
     */
    private static void saveData() {
        try {
            manager.saveToFile("Database.txt");
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads student data from file (if available).
     */
    private static void loadData() {
        try {
            manager.loadFromFile("Database.txt");
            System.out.println("Previous data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found or error loading. Starting with empty records.");
        }
    }
}

import java.util.Scanner;

/**
 * Helper class for input validation and handling.
 */
class InputeHelper {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Gets a valid integer input from the user.
     * @param prompt The message to display
     * @return Validated integer
     */
    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    /**
     * Gets a valid grade input (0-100) from the user.
     * @param prompt The message to display
     * @return Validated grade
     */
    public static double getGrade(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                double grade = Double.parseDouble(scanner.nextLine());
                if (grade >= 0 && grade <= 100) {
                    return grade;
                }
                System.out.println("Grade must be between 0 and 100!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Gets a string input from the user.
     * @param prompt The message to display
     * @return User input string
     */
    public static String getString(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }
}
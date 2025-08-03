import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Manages student records including their courses and grades.
 * Provides functionality to add, remove, update students and courses,
 * generate reports, and persist data to files.
 * Implements Serializable to support object serialization for data persistence.
 */
public class StudentManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Student> students;

    /**
     * Initializes a new StudentManager with an empty student list.
     */
    public StudentManager() {
        this.students = new ArrayList<>();
    }

    /**
     * Gets the list of all students.
     * @return List of Student objects
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Adds a new student with their enrolled courses.
     * Validates all grades to ensure they are between 0-100.
     * @param student The student to add
     */
    public void addStudent(Student student) {
        int courseCount = InputeHelper.getInt("How many courses has the student enrolled in?");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("\nCourse Details #" + (i + 1));
            String courseId = InputeHelper.getString("Enter Course ID: ");
            String courseName = InputeHelper.getString("Enter Course Name: ");
            double grade = InputeHelper.getGrade("Enter Grade (0-100): ");
            student.addCourses(new Course(courseId, courseName, grade));
        }
        students.add(student);
        System.out.println("\nStudent with courses successfully added.");
    }

    /**
     * Removes a student by their ID.
     * @param studentId The ID of the student to remove
     * @return true if student was found and removed, false otherwise
     */
    public boolean removeStudent(String studentId) {
        return students.removeIf(s -> s.getStudentId().equals(studentId));
    }

    /**
     * Updates course information for a student.
     * @param studentId The ID of the student
     * @param courseId The ID of the course to update
     */


    public void updateCourse(String studentId, String courseId) {
        boolean studentFound = false;
        boolean courseFound = false;

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                studentFound = true;

                for (Course course : student.getCourses()) {
                    if (course.getCourseId().equals(courseId)) {
                        courseFound = true;

                        System.out.println("\nCurrent Course Details:");
                        System.out.println("1. Course Name: " + course.getCourseName());
                        System.out.println("2. Course ID: " + course.getCourseId());
                        System.out.println("3. Grade: " + course.getGrade());

                        System.out.println("\nEnter new values (press Enter to keep current):");
                        String newName = InputeHelper.getString("New course name [" + course.getCourseName() + "]: ");
                        String newId = InputeHelper.getString("New course ID [" + course.getCourseId() + "]: ");
                        String gradeInput = InputeHelper.getString("New grade [" + course.getGrade() + "]: ");

                        if (!newName.isEmpty()) course.setCourseName(newName);
                        if (!newId.isEmpty()) course.setCourseId(newId);
                        if (!gradeInput.isEmpty()) {
                            try {
                                double newGrade = Double.parseDouble(gradeInput);
                                if (newGrade >= 0 && newGrade <= 100) {
                                    course.setGrade(newGrade);
                                } else {
                                    System.out.println("Grade must be 0-100. Keeping old value.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid grade format. Keeping old value.");
                            }
                        }

                        System.out.println("Course updated successfully!");
                        break;
                    }
                }

                if (!courseFound) {
                    System.out.println("Course with ID " + courseId + " not found for student " + studentId);
                }

                break;
            }
        }

        if (!studentFound) {
            System.out.println("Student with ID " + studentId + " not found!");
        }
    }


    /**
     * Adds a new course to a student's record.
     * @param studentId The ID of the student
     */
    public void addCourse(String studentId){
        for(Student s:students){
            if(s.getStudentId().equals(studentId)){
                String cName=InputeHelper.getString("Enter courseName: ");
                String cId=InputeHelper.getString("Enter course ID: ");
                double grad=InputeHelper.getGrade("Enter Grade:");
                s.addCourses(new Course(cId,cName,grad));
                System.out.println("Course added successfully!");
            }

        }

    }


    /**
     * Searches for a student and displays their complete report.
     * @param studentId The ID of the student to search for
     */
    public void searchStudent(String studentId) {
        students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .ifPresent(student -> {
                    GradeCalculator gc = new GradeCalculator();
                    System.out.println("\n------ STUDENT REPORT ------");
                    System.out.printf("%-15s: %s %s\n", "Full Name", student.getName(), student.getLastName());
                    System.out.printf("%-15s: %s\n", "ID", student.getStudentId());

                    System.out.println("\n--- ENROLLED COURSES ---");
                    System.out.printf("%-10s %-20s %s\n", "ID", "Course Name", "Grade");
                    student.getCourses().forEach(c ->
                            System.out.printf("%-10s %-20s %.2f\n",
                                    c.getCourseId(), c.getCourseName(), c.getGrade()));

                    System.out.println("\n--- GRADE SUMMARY ---");
                    System.out.printf("%-15s: %.2f\n", "Highest Grade", gc.get_HighestGrade(student));
                    System.out.printf("%-15s: %.2f\n", "Lowest Grade", gc.get_LowestGrade(student));
                    System.out.printf("%-15s: %.2f\n", "Average Grade", gc.get_Average(student));
                });
    }

    /**
     * Generates a comprehensive report of all students including:
     * - Individual student grade statistics
     * - Overall class statistics
     */
    public void studentReport() {
        if (students.isEmpty()) {
            System.out.println("No students found in records.");
            return;
        }

        GradeCalculator gc = new GradeCalculator();
        students.sort(Comparator.comparing(Student::getName));

        // Initialize overall statistics
        double classHighest = Double.MIN_VALUE;
        double classLowest = Double.MAX_VALUE;
        double sumAllGrades = 0;
        int totalCourses = 0;

        // Print report header
        System.out.println("\n=============== CLASS GRADE REPORT ===============");
        System.out.printf("%-20s %10s %10s %10s\n", "STUDENT NAME", "HIGHEST", "AVERAGE", "LOWEST");
        System.out.println("------------------------------------------------");

        // Process each student
        for (Student student : students) {
            double highest = gc.get_HighestGrade(student);
            double average = gc.get_Average(student);
            double lowest = gc.get_LowestGrade(student);

            // Update overall statistics
            classHighest = Math.max(classHighest, highest);
            classLowest = Math.min(classLowest, lowest);
            sumAllGrades += student.getCourses().stream().mapToDouble(Course::getGrade).sum();
            totalCourses += student.getCourses().size();

            // Print student row
            System.out.printf("%-20s %10.2f %10.2f %10.2f\n",
                    student.getName() + " " + student.getLastName(),
                    highest, average, lowest);
        }

        // Print overall statistics
        System.out.println("\n----------- OVERALL CLASS STATISTICS -----------");
        System.out.printf("%-20s: %.2f\n", "Highest Grade (Class)", classHighest);
        System.out.printf("%-20s: %.2f\n", "Lowest Grade (Class)", classLowest);
        System.out.printf("%-20s: %.2f\n", "Average Grade (Class)",
                totalCourses > 0 ? sumAllGrades / totalCourses : 0);
    }

    /**
     * Saves the student data to a file.
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        }
    }

    /**
     * Loads student data from a file.
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the class of a serialized object cannot be found
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
        }
    }
}


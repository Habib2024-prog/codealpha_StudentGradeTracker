import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a student with personal information and a list of enrolled courses.
 * Implements Serializable so that we can save and load the student data.
 */
public class Student implements Serializable {

    private String name;        // Student's first name
    private String lastName;    // Student's last name
    private String studentId;   // Unique ID for each student
    private List<Course> course; // A list to hold the courses the student is taking

    // Constructor to initialize student information
    public Student(String name, String lastName, String studentId) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.course = new ArrayList<>();
    }

    // Getter methods to access student data
    public String getStudentId() { return studentId; }

    public String getName() { return name; }

    public List<Course> getCourses() { return course; }

    public String getLastName() { return lastName; }

    // Setter methods to update student information if needed later
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public void addCourses(Course courses) { this.course.add(courses); }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setName(String name) { this.name = name; }
}

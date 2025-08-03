import java.io.Serializable;

/**
 * This class represents a course taken by a student.
 * It contains the course ID, name, and the student's grade in that course.
 * Implements Serializable so it can be saved and loaded from a file.
 */
public class Course implements Serializable {

    private String courseId;      // Unique identifier for the course
    private String courseName;    // Name of the course
    private double grade;         // Grade the student received

    // Constructor to initialize course information
    public Course(String courseId, String courseName, double grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    // Getter methods to access the data
    public String getCourseId() { return courseId; }

    public String getCourseName() { return courseName; }

    public double getGrade() { return grade; }

    // Setter methods to update the data if needed
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setGrade(double grade) { this.grade = grade; }
}

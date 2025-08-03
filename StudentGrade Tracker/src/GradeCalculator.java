import java.io.Serializable;

/**
 * This class helps calculate grades for a student.
 * It includes methods to find the highest, lowest, and average grade.
 * Implements Serializable so it can be saved and loaded if needed.
 */
public class GradeCalculator implements Serializable {

    private Student student;  // The student whose grades will be calculated

    // Default constructor
    public GradeCalculator() {
    }

    /**
     * Finds and returns the highest grade of a student.
     *
     * @param student the student to check
     * @return the highest grade among the student's courses
     */
    public double get_HighestGrade(Student student) {
        double highest = 0.0;
        for (Course course : student.getCourses()) {
            if (course.getGrade() > highest) {
                highest = course.getGrade();
            }
        }
        return highest;
    }

    /**
     * Finds and returns the lowest grade of a student.
     *
     * @param student the student to check
     * @return the lowest grade among the student's courses
     */
    public double get_LowestGrade(Student student) {
        double lowest = Double.MAX_VALUE;
        for (Course course : student.getCourses()) {
            if (course.getGrade() < lowest) {
                lowest = course.getGrade();
            }
        }
        return lowest;
    }

    /**
     * Calculates and returns the average grade of a student.
     *
     * @param student the student to check
     * @return the average of all course grades
     */
    public double get_Average(Student student) {
        double sum = 0.0;
        for (Course course : student.getCourses()) {
            sum += course.getGrade();
        }
        return sum / student.getCourses().size();
    }
}

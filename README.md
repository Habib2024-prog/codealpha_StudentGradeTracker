
## 📊 Student Grade Tracker
- The Student Grade Tracker is a Java-based console application for managing student records, courses, and grades.
- It allows you to add, remove, update, and search for student information, as well as generate detailed grade reports. 
- The application supports file-based data persistence using Java Serialization.
## 🚀 Features
- **Add, remove, and update student information**
- **Add and modify enrolled courses with grade validation**
- **Generate detailed reports**:
- **Individual student grade summaries**

- **Overall class grade statistics (highest, lowest, average)**
- **Save and load student data to/from files**
- **Robust input validation for safe user interactions**
##  Project Structure
- **src/**
- **│**
- **├── StudentManagerApp.java**                  # Entry point of the application
- **├── InputeHelper.java**          # Utility class for input validation
- **├── Course.java**                # Represents a course with ID, name, and grade
- **├── Student.java**               # Represents a student with personal info and course list
- **├── GradeCalculator.java**       # Calculates average, min, and max grades
- **└── StudentManager.java**        # Core class to manage student operations
##  Requirements
- **Java 8+ or higher**
- **A terminal or IDE to compile and run the program(IntelliJ IDEA)**
##    How to Run
- 1. clone the Repository
- 2. Open with IntelliJ IDEA
- 3. Run the **StudentManagerApp.java**
- **Note**: This file launches the interactive menu-driven console app.
##  Input Validation
- Grade input is restricted between 0 and 100.
- Invalid numeric/text inputs are safely re-prompted.
- All changes can be confirmed or skipped without crashing the program.
##  Data Persistence
- Data is serialized to a binary file **(Database.txt)**.
- Allowing the system to restore its state even after the application is closed.
##   Future Improvements 
- GUI version using JavaFX or Swing
- Export reports to CSV or PDF
- Sorting/filtering capabilities by grade or course
## Author
- **Habibullah Khaliqyar**
- Bs DataScience,NUST University,Islamabad 

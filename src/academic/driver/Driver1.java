package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author 12S23013 Andika Immanuel Nadapdap
 * @author 12S23033 Oloan Nainggolan
 */
public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) {
                break;
            }

            String[] parts = input.split("#");
            if (parts.length > 0) {
                String command = parts[0];
                switch (command) {
                    case "course-add":
                        if (parts.length == 5) {
                            String code = parts[1];
                            String name = parts[2];
                            int credits = Integer.parseInt(parts[3]);
                            String grade = parts[4];
                        
                            courses.add(new Course(code, name, credits, grade));
                        }
                        
                        break;
                    case "student-add":
                        if (parts.length == 5) {
                            String code = parts[1];
                            String name = parts[2];
                            String year = parts[3];
                            String major = parts[4];
                            students.add(new Student(code, name, year, major));
                        }
                        Collections.sort(courses, Comparator.comparing(Course::getCode));
                        Collections.sort(enrollments, Comparator.comparing(Enrollment::getCode));
                        break;
                    case "enrollment-add":
                        if (parts.length == 5) {
                            String courseCode = parts[1];
                            String studentId = parts[2];
                            String year = parts[3];
                            String semester = parts[4];
                            String[] defaultNotes = {"None"};
                            enrollments.add(new Enrollment(courseCode, studentId, year, semester, defaultNotes));
                        }
                        break;
                }
            }
        }
        scanner.close();

        for (Course course : courses) {
            System.out.println(course);
        }
        for (Student student : students) {
            System.out.println(student);
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}
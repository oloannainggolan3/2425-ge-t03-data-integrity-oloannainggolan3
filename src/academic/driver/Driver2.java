package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.Scanner;

/**

 */
public class Driver2 {
    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        

        Course[] courses = new Course[100];
        Student[] students = new Student[100];
        Enrollment[] enrollments = new Enrollment[100];

        int courseCount = 0;
        int studentCount = 0;
        int enrollmentCount = 0;

        StringBuilder invalidEntries = new StringBuilder();

        while (true) {
            String line = scanner.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");
            if (parts.length > 0) {
                String command = parts[0];

                switch (command) {
                    case "course-add":
                        if (parts.length == 5) {
                            String code = parts[1];
                            String name = parts[2];
                            int credits = Integer.parseInt(parts[3]);
                            String grade = parts[4];
                            courses[courseCount++] = new Course(code, name, credits, grade);
                        } else {
                            invalidEntries.append("invalid course-add command|").append(line).append("\n");
                        }
                        break;

                    case "student-add":
                        if (parts.length == 5) {
                            String code = parts[1];
                            String name = parts[2];
                            String year = parts[3];
                            String major = parts[4];
                            students[studentCount++] = new Student(code, name, year, major);
                        } else {
                            invalidEntries.append("invalid student-add command|").append(line).append("\n");
                        }

                        break;

                    case "enrollment-add":
                        if (parts.length == 5) {
                            String courseCode = parts[1];
                            String studentId = parts[2];
                            String year = parts[3];
                            String semester = parts[4];
                            String[] defaultNotes = {"None"};

                            boolean courseExists = false;
                            boolean studentExists = false;

                            for (int i = 0; i < courseCount; i++) {
                                if (courses[i].getCode().equals(courseCode)) {
                                    courseExists = true;
                                    break;
                                }
                            }

                            for (int i = 0; i < studentCount; i++) {
                                if (students[i].getCode().equals(studentId)) {
                                    studentExists = true;
                                    break;
                                }
                            }

                            if (!courseExists) {
                                invalidEntries.append("invalid course|").append(courseCode).append("\n");
                            } else if (!studentExists) {
                                invalidEntries.append("invalid student|").append(studentId).append("\n");
                            } else {
                                enrollments[enrollmentCount++] = new Enrollment(courseCode, studentId, year, semester, defaultNotes);
                            }
                        } else {
                            invalidEntries.append("invalid enrollment-add command|").append(line).append("\n");
                        }
                        break;

                    default:
                        invalidEntries.append("unknown command|").append(command).append("\n");
                }
            }
        }

        scanner.close();

        System.out.print(invalidEntries.toString());

  
        for (int i = courseCount - 1; i >= 0; i--) {
            System.out.println(courses[i].toString());
        }


        for (int i = studentCount - 1; i >= 0; i--) {
            System.out.println(students[i]);
        }

        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println(enrollments[i].toString());

        }
    }
}
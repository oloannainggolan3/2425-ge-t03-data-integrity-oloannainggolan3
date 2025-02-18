package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menggunakan array dengan kapasitas maksimum 100 elemen
        Course[] courses = new Course[100];
        Student[] students = new Student[100];
        Enrollment[] enrollments = new Enrollment[100];
        String[] invalidMessages = new String[100];

        int courseCount = 0, studentCount = 0, enrollmentCount = 0, invalidCount = 0;

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
                            String code = parts[1].trim();
                            String name = parts[2].trim();
                            int credits = Integer.parseInt(parts[3].trim());
                            String grade = parts[4].trim();
                            courses[courseCount++] = new Course(code, name, credits, grade);
                        }
                        break;

                    case "student-add":
                        if (parts.length == 5) {
                            String code = parts[1].trim();
                            String name = parts[2].trim();
                            String year = parts[3].trim();
                            String major = parts[4].trim();
                            students[studentCount++] = new Student(code, name, year, major);
                        }
                        break;

                    case "enrollment-add":
                        if (parts.length == 5) {
                            String courseCode = parts[1].trim();
                            String studentId = parts[2].trim();
                            String year = parts[3].trim();
                            String semester = parts[4].trim();
                            String[] defaultNotes = {"None"};

                            // Cek apakah course dan student ada dalam daftar
                            boolean courseExists = false, studentExists = false;

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
                                invalidMessages[invalidCount++] = "invalid course|" + courseCode;
                            } 
                            if (!studentExists) {
                                invalidMessages[invalidCount++] = "invalid student|" + studentId;
                            }
                            if (courseExists && studentExists) {
                                enrollments[enrollmentCount++] = new Enrollment(courseCode, studentId, year, semester, defaultNotes);
                            }
                        }
                        break;
                }
            }
        }
        scanner.close();

        // Tampilkan pesan invalid terlebih dahulu
        for (int i = 0; i < invalidCount; i++) {
            System.out.println(invalidMessages[i]);
        }

        // Cetak daftar courses
        for (int i = courseCount - 1; i >= 0; i--) {
            System.out.println(courses[i]);
        }
        
        
        
        // Cetak daftar students
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }

        // Cetak daftar enrollments yang valid
        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println(enrollments[i]);
        }
    }
}

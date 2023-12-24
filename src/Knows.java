import java.util.ArrayList;
import java.util.Scanner;

public class Knows {
    private static Scanner input = new Scanner(System.in);
    private static Admin[] admins = { new Admin("admin", "admin123", "Admin") };
    static int choice;

    public static void main(String[] args) {
        mainPage();
        System.out.println("\n\nKNOWS. All Rights Reserved. Copyright 2023.\n");
    }

    static void mainPage() {
        Display.logo();
        choice = Display.menu(new String[] { "Admin", "Faculty", "Student" });
        if (choice == 1) {
            adminPage();
        } else if (choice == 2) {
            facultyPage();
        } else if (choice == 3) {
            studentPage();
        } else {
            return;
        }
    }

    static void adminPage() {
        Display.logo();
        System.out.println("[Admin Login]");
        System.out.println();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            String username, password;
            System.out.print("Username: ");
            username = input.nextLine();
            for (Admin admin : admins) {
                if (username.compareTo(admin.getCredential().getUsername()) == 0) {
                    System.out.print("Password: ");
                    password = input.nextLine();
                    if (admin.getCredential().validatePin(password)) {
                        adminPortal();
                    } else {
                        adminPage();
                    }
                }
            }
            adminPage();

        } else {
            mainPage();
        }
    }

    static void adminPortal() {
        Display.logo();
        System.out.println("[Admin Portal]");
        System.out.println();
        choice = Display.menu(
                new String[] { "Register Student", "Resgister Faculty Member", "Register Course", "End Semester" });
        if (choice == 1) {
            System.out.println("Provide Student Details");
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            ArrayList<Course> courseList = chooseCourse(new ArrayList<Course>());

            Course[] studentCourses = new Course[courseList.size()];

            for (int i = 0; i < courseList.size(); i++) {
                studentCourses[i] = courseList.get(i);
            }

            Admin.registerStudent(username, password, name, studentCourses);
            adminPortal();

        } else if (choice == 2) {
            Display.logo();
            System.out.println("Provide Faculty member details");
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            Course facultyCourse = chooseCourse();

            Admin.registerFaculty(username, password, username, facultyCourse);
            adminPortal();

        } else if (choice == 3) {
            System.out.println("Provide Course Details");
            System.out.print("Course Name: ");
            String name = input.nextLine();
            System.out.print("Course ID: ");
            String id = input.nextLine();
            System.out.print("Theory Hours: ");
            int theoryHours = input.nextInt();
            input.nextLine();
            System.out.print("Lab Hours: ");
            int labHours = input.nextInt();
            input.nextLine();

            Admin.registerCourse(id, name, theoryHours, labHours);
            adminPortal();
        } else if (choice == 0) {
            adminPage();
        }
    }

    private static Course chooseCourse() {
        Display.logo();
        System.out.println("Choose a course ");
        ArrayList<Course> courses = Admin.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ": " + courses.get(i).getName());
        }
        System.out.println("0: Exit");
        choice = Display.chooseOption(courses.size());
        if (choice == 0) {
            return chooseCourse();
        }
        return courses.get(choice - 1);
    }

    private static ArrayList<Course> chooseCourse(ArrayList<Course> studentCourses) {
        Display.logo();
        System.out.println("Choose a course ");
        ArrayList<Course> courses = Admin.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ": " + courses.get(i).getName());
        }
        System.out.println("0: Exit");
        choice = Display.chooseOption(courses.size());
        if (choice != 0) {
            studentCourses.add(courses.get(choice - 1));
            return chooseCourse(studentCourses);
        }
        return studentCourses;
    }

    static void facultyPage() {
        Display.logo();
        System.out.println();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            System.out.println("This is faculty login");
        } else {
            mainPage();
        }
    }

    static void studentPage() {
        Display.logo();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            String username, password;
            System.out.print("Username: ");
            username = input.nextLine();
            ArrayList<Student> students = Admin.getStudents();
            for (Student student : students) {
                if (username.compareTo(student.getCredential().getUsername()) == 0) {
                    password = input.nextLine();
                    if (student.getCredential().validatePin(password)) {
                        studentPortal(student);
                    } else {
                        studentPage();
                    }
                } else {
                    studentPage();
                }
            }
            studentPage();
        } else {
            mainPage();
        }
    }

    static void studentPortal(Student student) {
        Display.logo();
        choice = Display.menu(new String[] { "Student details", "Course Summary" });
        if (choice == 1) {
            System.out.print("Name: " + student.getName());
            System.out.println("Username: " + student.getCredential().getUsername());
        } else if (choice == 2) {
            semesterSummary(student);
        } else {
            studentPage();
        }
    }

    static void semesterSummary(Student student) {
        ArrayList<Marks> registeredCourses = student.getSemesters().get((student.getSemesters().size()) - 1)
                .getRegisteredCourses();
        for (Marks mark : registeredCourses) {
            System.out.println(mark.getCourse().getId() + ": " + mark.getCourse().getName());
        }
        System.out.println("0: Exit");
        choice = Display.chooseOption(registeredCourses.size() - 1);
        if (choice == 0) {
            studentPortal(student);
        } else {
            courseSummary(student, registeredCourses.get(choice - 1));
        }
    }

    static void courseSummary(Student student, Marks mark) {
        Display.logo();
        System.out.println(mark.toString());
        choice = Display.menu(new String[] {});
        if (choice == 0) {
            semesterSummary(student);
        }
    }

}

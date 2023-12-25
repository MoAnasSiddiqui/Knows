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
        }
    }

    static void adminPage() {
        Display.logo();
        System.out.println("[Admin Login]");
        System.out.println();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            adminLogin();
        } else {
            mainPage();
        }
    }

    static void adminLogin() {
        Display.logo();
        System.out.println("[Admin Login]");
        System.out.println("\n");
        String username, password;
        System.out.print("Enter Username: ");
        username = input.nextLine();
        for (Admin admin : admins) {
            if (username.compareTo(admin.getCredential().getUsername()) == 0) {
                System.out.print("Enter Password: ");
                password = input.nextLine();
                if (admin.getCredential().validatePin(password)) {
                    System.out.print("\nLogin successful\n\nPress Enter to continue...");
                    input.nextLine();
                    adminPortal();
                } else {
                    System.out.print("\nIncorrect Password\n\nPress Enter to to go back...");
                    input.nextLine();
                    adminPage();
                }
            }
        }
        System.out.print("\nCannot find username\n\nPress Enter to to go back...");
        input.nextLine();
        adminPage();
    }

    static void adminPortal() {
        Display.logo();
        System.out.println("[Admin Portal]");
        System.out.println();
        choice = Display.menu(
                new String[] { "Register Student", "Register Faculty Member", "Register Course", "End Semester" });
        if (choice == 1) {
            Display.logo();
            System.out.println("[Student Registration]");
            System.out.println("\n");
            System.out.println("Provide Student Details\n");
            System.out.print("Student name: ");
            String name = input.nextLine();
            System.out.print("Student username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            ArrayList<Course> courseList = chooseCourse(new ArrayList<Course>());

            Course[] studentCourses = new Course[courseList.size()];

            for (int i = 0; i < courseList.size(); i++) {
                studentCourses[i] = courseList.get(i);
            }

            Admin.registerStudent(username, password, name, studentCourses);
            System.out.print("\n" + name + " successfully registered\n\nPress Enter to continue...");
            input.nextLine();
            adminPortal();

        } else if (choice == 2) {
            Display.logo();
            System.out.println("[Faculty Registration]");
            System.out.println("\n");
            System.out.println("Provide Faculty member details\n");
            System.out.print("Faculty name: ");
            String name = input.nextLine();
            System.out.print("Faculty username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            Course facultyCourse = chooseCourse();

            Admin.registerFaculty(username, password, username, facultyCourse);
            System.out.print("\n" + name + " successfully registered\n\nPress Enter to continue...");
            input.nextLine();
            adminPortal();

        } else if (choice == 3) {
            Display.logo();
            System.out.println("[Course Registration]");
            System.out.println("\n");
            System.out.println("Provide Course Details\n");
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
            System.out.print("\n" + name + " successfully registered\n\nPress Enter to continue...");
            input.nextLine();
            adminPortal();
        } else if (choice == 0) {
            adminPage();
        }
    }

    private static Course chooseCourse() {
        Display.logo();
        System.out.println("\nChoose a course\n");
        ArrayList<Course> courses = Admin.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ": " + courses.get(i).getName());
        }
        System.out.println("0: Exit\n");
        choice = Display.chooseOption(courses.size());
        if (choice == 0) {
            return chooseCourse();
        }
        return courses.get(choice - 1);
    }

    private static ArrayList<Course> chooseCourse(ArrayList<Course> studentCourses) {
        Display.logo();
        System.out.println("Choose a course\n");
        ArrayList<Course> courses = Admin.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ": " + courses.get(i).getName());
        }
        System.out.println("0: Exit\n");
        choice = Display.chooseOption(courses.size());
        if (choice != 0) {
            studentCourses.add(courses.get(choice - 1));
            System.out.print("\nSuccessfully registered " + courses.get(choice - 1).getName()
                    + "\n\nPress Enter to continue...");
            input.nextLine();
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
            studentLogin();
        } else {
            mainPage();
        }
    }

    static void studentLogin() {
        Display.logo();
        System.out.println("[Student Login]");
        System.out.println("\n");
        String username, password;
        System.out.print("Enter Username: ");
        username = input.nextLine();
        for (Student student : Admin.getStudents()) {
            if (username.compareTo(student.getCredential().getUsername()) == 0) {
                System.out.print("Enter Password: ");
                password = input.nextLine();
                if (student.getCredential().validatePin(password)) {
                    System.out.print("\nLogin successful\n\nPress Enter to continue...");
                    input.nextLine();
                    studentPortal(student);
                } else {
                    System.out.print("\nIncorrect Password\n\nPress Enter to to go back...");
                    input.nextLine();
                    studentPage();
                }
            }
        }
        System.out.print("\nCannot find username\n\nPress Enter to to go back...");
        input.nextLine();
        studentPage();
    }

    static void studentPortal(Student student) {
        Display.logo();
        choice = Display.menu(new String[] { "Student details", "Course Summary" });
        if (choice == 1) {
            Display.logo();
            System.out.println("[Student details]");
            System.out.println("\n");
            System.out.print("Name: " + student.getName());
            System.out.println("\nUsername: " + student.getCredential().getUsername());
            System.out.print("\nPress Enter to go back...");
            input.nextLine();
            studentPortal(student);
        } else if (choice == 2) {
            semesterSummary(student);
        } else {
            studentPage();
        }
    }

    static void semesterSummary(Student student) {
        Display.logo();
        System.out.println("[Current Semester Summary]");
        System.out.println("\n");
        ArrayList<Marks> registeredCourses = student.getSemesters().get((student.getSemesters().size()) - 1)
                .getRegisteredCourses();
        int iterator = 1;
        for (Marks mark : registeredCourses) {
            System.out.println(iterator + ": Course ID: " + mark.getCourse().getId() + " - Course Name: "
                    + mark.getCourse().getName());
            iterator++;
        }
        System.out.println("0: Exit\n");
        choice = Display.chooseOption(registeredCourses.size());
        if (choice == 0) {
            studentPortal(student);
        } else {
            courseSummary(student, registeredCourses.get(choice - 1));
        }
    }

    static void courseSummary(Student student, Marks mark) {
        Display.logo();
        System.out.println("[" + mark.getCourse().getName() + " Summary]");
        System.out.println("\n");
        System.out.println(mark.toString());
        System.out.print("\nPress Enter to go back...");
        input.nextLine();
        semesterSummary(student);
    }
}

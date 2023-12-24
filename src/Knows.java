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
        System.out.println("[Admin Portal]");
        System.out.println();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            String username, password;
            System.out.print("Username: ");
            username = input.nextLine();
            for (Admin admin : admins) {
                if (username == admin.getCredential().getUsername()) {
                    password = input.nextLine();
                    if (admin.getCredential().validatePin(password)) {
                        adminPortal();
                    } else {
                        adminPage();
                    }
                }
            }

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

            Course[] studentCourses = (Course[]) chooseCourse(new ArrayList<Course>()).toArray();

            Admin.registerStudent(username, password, name, studentCourses);

        } else if (choice == 2) {

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
        }
    }

    private static ArrayList<Course> chooseCourse(ArrayList<Course> studentCourses) {
        Display.logo();
        ArrayList<Course> courses = Admin.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ": " + courses.get(i).getName());
        }
        System.out.println("0: Exit");
        System.out.println("Choose Course:");
        choice = Display.chooseOption(courses.size());
        if (choice != 0) {
            studentCourses.add(courses.get(choice));
            chooseCourse(studentCourses);
        }
        return studentCourses;
    }

    static void facultyPage() {
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            System.out.println("This is faculty login");
        } else {
            mainPage();
        }
    }

    static void studentPage() {
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            System.out.println("This is student login");
        } else {
            mainPage();
        }
    }

}

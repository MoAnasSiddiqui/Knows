import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Knows implements Serializable {
    private static Scanner input = new Scanner(System.in);
    private static Admin[] admins = { new Admin("a", "a", "a") };
    static int choice;

    public static void main(String[] args) {
        input.nextLine();
        mainPage();
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
        } else if (choice == 0) {
            System.out.println("\n\nKNOWS. All Rights Reserved. Copyright 2023.\n");
            System.exit(0);
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
                new String[] { "Register Student", "Register Faculty Member", "Register Course for KNOWS",
                        "Register Course for Student", "End Semester" });
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

            Admin.registerStudent(username, password, name);
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

            Admin.registerFaculty(username, password, name, facultyCourse);
            System.out.print("\n" + name + " successfully registered\n\nPress Enter to continue...");
            input.nextLine();
            adminPortal();

        } else if (choice == 3) {
            Display.logo();
            System.out.println("[Course Registration for KNOWS]");
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

            Admin.registerKNOWSCourse(id, name, theoryHours, labHours);
            System.out.print("\n" + name + " successfully registered\n\nPress Enter to continue...");
            input.nextLine();
            adminPortal();

        } else if (choice == 4) {
            Display.logo();
            System.out.println("[Course Registration for Students]");
            System.out.println("\n");
            for (int i = 0; i < Admin.getStudents().size(); i++) {
                System.out.println((i + 1) + ": " + Admin.getStudents().get(i).getCredential().getUsername());
            }
            System.out.println("0: Exit\n\n");
            choice = Display.chooseOption(Admin.getStudents().size());
            if (choice == 0) {
                adminPortal();
            } else {
                ArrayList<Course> studentCourses = new ArrayList<>();
                Student student = Admin.getStudents().get(choice - 1);
                ArrayList<Course> courses = chooseCourse(studentCourses);
                Admin.registerStudentCourses(student, courses);
                adminPortal();
            }
        } else {
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
        System.out.println(choice);
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
        } else {
            return studentCourses;
        }

    }

    static void facultyPage() {
        Display.logo();
        System.out.println();
        choice = Display.menu(new String[] { "Login" });
        if (choice == 1) {
            facultyLogin();
        } else {
            mainPage();
        }
    }

    static void facultyLogin() {
        Display.logo();
        System.out.println("[Faculty Login]");
        System.out.println("\n");
        String username, password;
        System.out.print("Enter Username: ");
        username = input.nextLine();
        for (Faculty faculty : Admin.getFaculty()) {
            if (username.compareTo(faculty.getCredential().getUsername()) == 0) {
                System.out.print("Enter Password: ");
                password = input.nextLine();
                if (faculty.getCredential().validatePin(password)) {
                    System.out.print("\nLogin successful\n\nPress Enter to continue...");
                    input.nextLine();
                    facultyPortal(faculty);
                } else {
                    System.out.print("\nIncorrect Password\n\nPress Enter to to go back...");
                    input.nextLine();
                    facultyPage();
                }
            }
        }
        System.out.print("\nCannot find username\n\nPress Enter to to go back...");
        input.nextLine();
        facultyPage();
    }

    static void facultyPortal(Faculty faculty) {
        Display.logo();
        choice = Display.menu(new String[] { "View faculty details", "Add Marks" });
        if (choice == 1) {
            Display.logo();
            System.out.println(faculty.toString());
            System.out.print("\nPress Enter to go back...");
            input.nextLine();
            facultyPortal(faculty);
        } else if (choice == 2) {
            ArrayList<Student> registeredStudents = faculty.getCourse().getRegisteredStudents();
            String[] studentNames = new String[registeredStudents.size()];
            for (int i = 0; i < studentNames.length; i++) {
                studentNames[i] = registeredStudents.get(i).getCredential().getUsername();
            }
            choice = Display.menu(studentNames);
            System.out.println("Choose the student you want to add marks of from the above students list.");
            if (choice != 0) {
                Student selectedStudent = registeredStudents.get(choice - 1);
                Display.logo();
                choice = Display.menu(new String[] { "Add Assigment Marks", "Add Quiz Marks",
                        "Add Lab Assignment Marks", "Add Mid Marks", "Add Terminal Marks" });
                if (choice == 1) {
                    System.out.print("Enter Assignment Number: ");
                    int num = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Assignment Marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();
                    faculty.updateAssignmentMarks(selectedStudent, num, marks);
                    System.out.print("Assignment Marks successfully added");
                } else if (choice == 2) {
                    System.out.print("Enter Quiz Number: ");
                    int num = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Quiz Marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();
                    faculty.updateQuizMarks(selectedStudent, num, marks);
                    System.out.print("Quiz Marks successfully added");
                } else if (choice == 3) {
                    System.out.print("Enter Lab Assignment Marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();
                    faculty.updateLabAssignmentMarks(selectedStudent, marks);
                    System.out.print("Lab Assignment Marks successfully added");
                } else if (choice == 4) {
                    System.out.print("Enter 0 for Theory Mid and 1 for Lab Mid: ");
                    int num = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Mid Marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();
                    faculty.updateMidMarks(selectedStudent, num, marks);
                    System.out.print("Mid Marks successfully added");
                } else if (choice == 5) {
                    System.out.print("Enter 0 for Theory Terminal and 1 for Lab Terminal: ");
                    int num = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Terminal Marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();
                    faculty.updateTerminalMarks(selectedStudent, num, marks);
                    System.out.print("Terminal Marks successfully added");
                }
                System.out.print("\nPress Enter to go back...");
                input.nextLine();
                facultyPortal(faculty);
            }
        } else {
            facultyPage();
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
                .getRegisteredCourseMarks();
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

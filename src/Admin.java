import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultys;
  private static ArrayList<Course> courses;
  private static ObjectHandling<Student> studentFile;
  private static ObjectHandling<Faculty> facultyFile;
  private static ObjectHandling<Course> courseFile;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
    students = new ArrayList<>();
    facultys = new ArrayList<>();
    courses = new ArrayList<>();
    studentFile = new ObjectHandling<>("database/students.ser");
    facultyFile = new ObjectHandling<>("database/facultys.ser");
    courseFile = new ObjectHandling<>("database/courses.ser");

    students = studentFile.readFromFile(students);
    facultys = facultyFile.readFromFile(facultys);
    courses = courseFile.readFromFile(courses);
    System.out.println(students);
    System.out.println("\n\n");
    System.out.println(facultys);
    System.out.println("\n\n");
    System.out.println(courses);
    System.out.println("\n\n");

    // registerStudent("anas", "anas", "anas");
    // registerStudent("asfer", "asfer", "asfer");
    // registerKNOWSCourse("oop", "oop", 3, 1);
    // registerKNOWSCourse("pst", "pst", 3, 0);
    // registerFaculty("sajida", "sajida", "sajida", courses.get(0));
    // registerFaculty("rabia", "rabia", "rabia", courses.get(1));
    // ArrayList<Course> temp0 = new ArrayList<>();
    // temp0.add(courses.get(0));
    // registerStudentCourses(students.get(0), temp0);
    // ArrayList<Course> temp1 = new ArrayList<>();
    // temp1.add(courses.get(1));
    // registerStudentCourses(students.get(1), temp1);
  }

  public static void registerStudent(String username, String password, String name) {
    Student newStudent = new Student(username, password, name);
    Semester newSemester = new Semester();
    newStudent.addSemester(newSemester);
    students.add(newStudent);

    // Write in file
    studentFile.writeInFile(newStudent);
  }

  public static void registerStudentCourses(Student student, ArrayList<Course> registeredCourses) {
    ArrayList<Marks> courseMarks = new ArrayList<>();
    for (Course course : registeredCourses) {
      course.registerStudentInCourse(student);
      courseMarks.add(new Marks(course));
    }
    student.getLastSemester().addRegisteredCourseMarks(courseMarks);

    // Overwrite the whole file to update the student and course objects in the file
    updateData();
  }

  public static void registerFaculty(String username, String password, String name, Course course) {
    Faculty newFaculty = new Faculty(username, password, name, course);
    facultys.add(newFaculty);

    // Write in file
    facultyFile.writeInFile(newFaculty);
  }

  public static void registerKNOWSCourse(String id, String name, int theoryHours, int labHours) {
    Course newCourse = new Course(id, name, theoryHours, labHours);
    courses.add(newCourse);

    // Write in file
    courseFile.writeInFile(newCourse);
  }

  public static ArrayList<Course> getCourses() {
    return courses;
  }

  public static ArrayList<Student> getStudents() {
    return students;
  }

  public static ArrayList<Faculty> getFaculty() {
    return facultys;
  }

  public static void updateData() {
    studentFile.updateFile(students);
    courseFile.updateFile(courses);
    facultyFile.updateFile(facultys);
  }

}

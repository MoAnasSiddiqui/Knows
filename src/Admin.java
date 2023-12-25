import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultyMembers;
  private static ArrayList<Course> courses;
  private static ObjectHandling<Student> studentFile;
  private static ObjectHandling<Faculty> facultyFile;
  private static ObjectHandling<Course> courseFile;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
    students = new ArrayList<>();

    facultyMembers = new ArrayList<>();
    courses = new ArrayList<>();

    studentFile = new ObjectHandling<>("students.txt");
    facultyFile = new ObjectHandling<>("facultys.txt");
    courseFile = new ObjectHandling<>("courses.txt");
    studentFile.readFromFile(students);
    facultyFile.readFromFile(facultyMembers);
    courseFile.readFromFile(courses);
    // registerStudent("FA22-BCS-045", "anas045", "Anas");
    // registerStudent("FA22-BCS-017", "asfer017", "Asfer");
    // registerCourse("CSC110", "OOP", 3, 1);
    // registerCourse("CSC210", "DSA", 3, 1);
    // registerFaculty("SajidaKulsoom", "sajida", "Sajida Kulsoom", courses.get(0));
    // registerFaculty("InayatUrRehman", "inayat", "Inayat Ur Rehman",
    // courses.get(1));
  }

  public static void registerStudent(String username, String password, String name) {
    Student newStudent = new Student(username, password, name);
    Semester semester = new Semester();
    newStudent.addSemester(semester);
    students.add(newStudent);
    studentFile.writeInFile(newStudent);
  }

  public static void registerStudentCourses(Student student, ArrayList<Course> registeredCourses) {
    ArrayList<Marks> marksObjs = new ArrayList<>();
    for (Course c : registeredCourses) {
      c.registerStudent(student);
      marksObjs.add(new Marks(c));
    }
    student.getLastSemester().setRegisteredCourses(marksObjs);
    courseFile.updateFile(courses);
    studentFile.updateFile(students);
    facultyFile.updateFile(facultyMembers);
  }

  public static void registerFaculty(String username, String password, String name, Course course) {
    Faculty newFaculty = new Faculty(username, password, name, course);
    facultyMembers.add(newFaculty);
    facultyFile.writeInFile(newFaculty);
  }

  public static void registerCourse(String id, String name, int theoryHours, int labHours) {
    Course newCourse = new Course(id, name, theoryHours, labHours);
    courses.add(newCourse);
    courseFile.writeInFile(newCourse);
  }

  public static ArrayList<Course> getCourses() {
    return courses;
  }

  public static ArrayList<Student> getStudents() {
    return students;
  }

  public static ArrayList<Faculty> getFacultyMembers() {
    return facultyMembers;
  }

  public static void updateStudentFile() {
    studentFile.updateFile(students);
    courseFile.updateFile(courses);
    facultyFile.updateFile(facultyMembers);
  }

  public static void updateCourseFile() {
    courseFile.updateFile(courses);
  }

}

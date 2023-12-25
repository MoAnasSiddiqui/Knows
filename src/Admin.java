import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> faculty;
  private static ArrayList<Course> courses;
  private static ObjectHandling<Student> studentFile;
  private static ObjectHandling<Faculty> facultyFile;
  private static ObjectHandling<Course> courseFile;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
    students = new ArrayList<>();

    faculty = new ArrayList<>();
    courses = new ArrayList<>();

    studentFile = new ObjectHandling<>("students.txt");
    facultyFile = new ObjectHandling<>("facultys.txt");
    courseFile = new ObjectHandling<>("courses.txt");
    students = studentFile.readFromFile(students);
    faculty = facultyFile.readFromFile(faculty);
    courses = courseFile.readFromFile(courses);
  }

  public static void registerStudent(String username, String password, String name) {
    Student newStudent = new Student(username, password, name);
    Semester newSemester = new Semester();
    newStudent.addSemester(newSemester);
    students.add(newStudent);
    studentFile.writeInFile(newStudent);
  }

  public static void registerStudentCourses(Student student, ArrayList<Course> registeredCourses) {
    ArrayList<Marks> courseMarks = new ArrayList<>();
    for (Course course : registeredCourses) {
      course.registerStudentInCourse(student);
      courseMarks.add(new Marks(course));
    }
    student.getLastSemester().setRegisteredCourseMarks(courseMarks);
    updateFile();
  }

  public static void registerFaculty(String username, String password, String name, Course course) {
    Faculty newFaculty = new Faculty(username, password, name, course);
    faculty.add(newFaculty);
    facultyFile.writeInFile(newFaculty);
  }

  public static void registerKNOWSCourse(String id, String name, int theoryHours, int labHours) {
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

  public static ArrayList<Faculty> getFaculty() {
    return faculty;
  }

  public static void updateFile() {
    studentFile.updateFile(students);
    courseFile.updateFile(courses);
    facultyFile.updateFile(faculty);
  }

}

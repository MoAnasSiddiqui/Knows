import java.util.ArrayList;

public class Admin extends Person {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultyMembers;
  private static ArrayList<Course> courses;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
  }

  public static void registerStudent(String username, String password, String name, Course[] registeredCourses) {
    Student newStudent = new Student(username, password, name);
    ArrayList<Marks> marksObjs = new ArrayList<>();
    for (Course c : registeredCourses) {
      marksObjs.add(new Marks(c));
    }
    Semester semester = new Semester(marksObjs);
    newStudent.addSemester(semester);
    students.add(newStudent);
  }

  public static void registerFaculty(String username, String password, String name, Course course) {
    Faculty newFaculty = new Faculty(username, password, name, course);
    facultyMembers.add(newFaculty);
  }

  public static void registerCourse(String id, String name, int theoryHours, int labHours) {
    Course newCourse = new Course(id, name, theoryHours, labHours);
    courses.add(newCourse);
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

}

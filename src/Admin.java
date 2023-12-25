import java.util.ArrayList;

public class Admin extends Person {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultyMembers;
  private static ArrayList<Course> courses;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
    students = new ArrayList<>();
    registerStudent("FA22-BCS-045", "Anas", "anas045");
    registerStudent("FA22-BCS-022", "Asfer", "asfer022");
    facultyMembers = new ArrayList<>();
    courses = new ArrayList<>();
    registerCourse("CSC110", "OOP", 3, 1);
    registerCourse("CSC210", "DSA", 3, 1);
    registerFaculty("SajidaKulsoom", "sajida", "Sajida Kulsoom", courses.get(0));
    registerFaculty("InayatUrRehman", "inayat", "Inayat Ur Rehman", courses.get(1));
  }

  public static void registerStudent(String username, String password, String name) {
    Student newStudent = new Student(username, password, name);
    Semester semester = new Semester();
    newStudent.addSemester(semester);
    students.add(newStudent);
  }

  public static void registerStudentCourses(Student student, ArrayList<Course> registeredCourses) {
    ArrayList<Marks> marksObjs = new ArrayList<>();
    for (Course c : registeredCourses) {
      marksObjs.add(new Marks(c));
    }
    student.getLastSemester().setRegisteredCourses(marksObjs);
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

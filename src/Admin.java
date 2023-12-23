import java.util.ArrayList;

public class Admin extends Person {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultyMembers;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
  }

  public void registerStudent(String username, String password, String name, Course[] registeredCourses) {
    Student newStudent = new Student(username, password, name);
    ArrayList<Marks> marksObjs = new ArrayList<>();
    for (Course c : registeredCourses) {
      marksObjs.add(new Marks(c));
    }
    Semester semester = new Semester(marksObjs);
    newStudent.addSemester(semester);
    students.add(newStudent);
  }

  public void registerFaculty(String username, String password, String name) {

  }

}

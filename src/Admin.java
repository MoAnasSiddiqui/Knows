import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person implements Serializable {
  private static ArrayList<Student> students;
  private static ArrayList<Faculty> facultys;
  private static ArrayList<Course> courses;
  private static ArrayList<ArrayList<Semester>> studentSemesters;
  private static ArrayList<ArrayList<Marks>> semesterMarks;
  private static ObjectHandling<Student> studentFile;
  private static ObjectHandling<Faculty> facultyFile;
  private static ObjectHandling<Course> courseFile;
  private static ObjectHandling<ArrayList<Semester>> semesterFile;
  private static ObjectHandling<ArrayList<Marks>> marksFile;

  Admin() {
  }

  public Admin(String username, String password, String name) {
    super(username, password, name);
    students = new ArrayList<>();
    facultys = new ArrayList<>();
    courses = new ArrayList<>();
    studentSemesters = new ArrayList<>();
    semesterMarks = new ArrayList<>();
    studentFile = new ObjectHandling<>("database/students.ser");
    facultyFile = new ObjectHandling<>("database/facultys.ser");
    courseFile = new ObjectHandling<>("database/courses.ser");
    semesterFile = new ObjectHandling<>("database/semester.ser");
    marksFile = new ObjectHandling<>("database/marks.ser");

    if (studentFile.exists())
      students = studentFile.readFromFile(students);
    if (facultyFile.exists())
      facultys = facultyFile.readFromFile(facultys);
    if (courseFile.exists())
      courses = courseFile.readFromFile(courses);
    if (semesterFile.exists())
      studentSemesters = semesterFile.readFromFile(studentSemesters);
    if (marksFile.exists())
      semesterMarks = marksFile.readFromFile(semesterMarks);

    for (int i = 0; i < studentSemesters.size(); i++) {
      ArrayList<Semester> sems = studentSemesters.get(i);
      Semester sem = sems.get(sems.size() - 1);
      if (semesterMarks.size() > i)
        sem.setRegisteredCourseMarks(semesterMarks.get(i));

      students.get(i).setSemesters(sems);
    }

    for (Course c : courses) {
      ArrayList<Student> rstudents = new ArrayList<>();
      for (Student rs : c.getRegisteredStudents()) {
        for (Student s : students) {
          if (s.getCredential().getUsername().compareTo(rs.getCredential().getUsername()) == 0) {
            rstudents.add(s);
          }
        }
      }
      c.setRegisteredStudents(rstudents);
    }

    for (Faculty f : facultys) {
      for (Course c : courses) {
        if (f.getCourse().getId().compareTo(c.getId()) == 0) {
          f.setCourse(c);
        }
      }
    }

    System.out.println(students);
    System.out.println("\n\n");
    System.out.println(facultys);
    System.out.println("\n\n");
    System.out.println(courses);
    System.out.println("\n\n");

  }

  public static void registerStudent(String username, String password, String name) {
    Student newStudent = new Student(username, password, name);
    Semester newSemester = new Semester();
    newStudent.addSemester(newSemester);
    students.add(newStudent);

    // Write in file
    updateSemesterFile();
    studentFile.writeInFile(newStudent);
  }

  public static void registerStudentCourses(Student student, ArrayList<Course> registeredCourses) {
    ArrayList<Marks> courseMarks = new ArrayList<>();
    // System.out.println(registeredCourses);
    for (Course course : registeredCourses) {
      courseMarks.add(new Marks(course.getId()));
      course.registerStudentInCourse(student);

    }
    student.getLastSemester().addRegisteredCourseMarks(courseMarks);

    // Overwrite the whole file to update the student and course objects in the file
    updateSemesterFile();
    updateMarksFile();
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

  public static Course getCourseById(String id) {
    for (Course c : courses) {
      if (c.getId().compareTo(id) == 0)
        return c;
    }
    return null;
  }

  public static void updateSemesterFile() {
    studentSemesters = new ArrayList<>();
    for (Student s : students) {
      studentSemesters.add(s.getSemesters());
    }
    semesterFile.updateFile(studentSemesters);
  }

  public static void updateMarksFile() {
    semesterMarks = new ArrayList<>();
    for (ArrayList<Semester> sems : studentSemesters) {
      semesterMarks.add(sems.get(sems.size() - 1).getRegisteredCourseMarks());
    }
    marksFile.updateFile(semesterMarks);
  }

  public static void updateData() {
    courseFile.updateFile(courses);
    studentFile.updateFile(students);
    facultyFile.updateFile(facultys);
  }

}

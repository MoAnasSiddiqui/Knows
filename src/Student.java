import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Semester> semesters;

    public Student() {
    };

    public Student(String username, String password, String name) {
        super(username, password, name);
    }

    public void addSemester(Semester semester) {
        semesters.add(semester);
    }
}
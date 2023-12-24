import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Semester> semesters;

    public Student() {
        semesters = new ArrayList<>();
    };

    public Student(String username, String password, String name) {
        super(username, password, name);
        semesters = new ArrayList<>();
    }

    public void addSemester(Semester semester) {
        semesters.add(semester);
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }
}
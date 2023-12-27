import java.io.Serializable;
import java.util.ArrayList;

public class Student extends Person implements Serializable {
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

    public Semester getLastSemester() {
        if (semesters.size() == 0) {
            return null;
        }
        return semesters.get(semesters.size() - 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
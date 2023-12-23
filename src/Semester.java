import java.util.ArrayList;

public class Semester {
    private ArrayList<Marks> registeredCourses;

    Semester() {
        registeredCourses = new ArrayList<Marks>();
    }

    Semester(ArrayList<Marks> regCourses) {
        registeredCourses = regCourses;
    }

    public void addCourse(Marks course) {
        registeredCourses.add(course);
    }

    @Override
    public String toString() {
        return registeredCourses.get(registeredCourses.size() - 1).toString();
    }
}

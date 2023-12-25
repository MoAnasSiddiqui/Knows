import java.io.Serializable;
import java.util.ArrayList;

public class Semester implements Serializable {
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

    public void setRegisteredCourses(ArrayList<Marks> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public Marks getMarksObj(Course course) {
        for (Marks m : registeredCourses) {
            if (m.getCourse().getId().compareTo(course.getId()) == 0) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Marks> getRegisteredCourses() {
        return registeredCourses;
    }

    @Override
    public String toString() {
        return registeredCourses.get(registeredCourses.size() - 1).toString();
    }
}

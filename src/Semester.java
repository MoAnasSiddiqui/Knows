import java.io.Serializable;
import java.util.ArrayList;

public class Semester implements Serializable {
    private ArrayList<Marks> registeredCourseMarks;

    Semester() {
        registeredCourseMarks = new ArrayList<Marks>();
    }

    Semester(ArrayList<Marks> regCourses) {
        registeredCourseMarks = regCourses;
    }

    public void addCourse(Marks course) {
        registeredCourseMarks.add(course);
    }

    public void addRegisteredCourseMarks(ArrayList<Marks> registeredCourseMarks) {
        for (Marks m : registeredCourseMarks) {
            this.registeredCourseMarks.add(m);
        }
    }

    public void addRegisteredCourseMarks(Course selectedCourse) {
        Marks registeredCourseMarks = new Marks(selectedCourse.getId());
        this.registeredCourseMarks.add(registeredCourseMarks);
    }

    public Marks getCourseMarks(Course course) {
        for (Marks m : registeredCourseMarks) {
            if (m.getCourseId().compareTo(course.getId()) == 0) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Marks> getRegisteredCourseMarks() {
        return registeredCourseMarks;
    }

    public void setRegisteredCourseMarks(ArrayList<Marks> registeredCourseMarks) {
        this.registeredCourseMarks = registeredCourseMarks;
    }

    @Override
    public String toString() {
        return registeredCourseMarks.get(registeredCourseMarks.size() - 1).toString();
    }
}

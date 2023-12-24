import java.util.ArrayList;

public class Faculty extends Person{
    private Course course;
    private ArrayList<Student> registeredStudents;
    Faculty(){}
    Faculty(String username, String password, String name, Course course){
        super(username, password, name);
        setCourse(course);
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public Course getCourse() {
        return course;
    }
}
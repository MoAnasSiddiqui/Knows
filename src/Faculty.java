public class Faculty extends Person{
    private Course course;
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
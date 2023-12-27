import java.io.Serializable;

public class Faculty extends Person implements Serializable {
    private Course course;

    Faculty() {
    }

    Faculty(String username, String password, String name, Course course) {
        super(username, password, name);
        setCourse(course);
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void updateAssignmentMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getCourseMarks(course);

        marksObj.updateAssignment(num, marks);

        // Admin.updateData();
    }

    public void updateQuizMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getCourseMarks(course);
        marksObj.updateQuiz(num, marks);

        // Admin.updateData();
    }

    public void updateLabAssignmentMarks(Student student, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getCourseMarks(course);

        marksObj.updateLabAssignment(marks);

        // Admin.updateData();
    }

    public void updateMidMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getCourseMarks(course);
        marksObj.updateMid(num, marks);

        // Admin.updateData();
    }

    public void updateTerminalMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getCourseMarks(course);

        marksObj.updateTerminal(num, marks);

        // Admin.updateData();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
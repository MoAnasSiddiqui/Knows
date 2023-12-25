import java.util.ArrayList;

public class Faculty extends Person {
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
        Marks marksObj = semester.getMarksObj(course);

        marksObj.updateAssignment(num, marks);
    }

    public void updateQuizMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getMarksObj(course);

        marksObj.updateQuiz(num, marks);
    }

    public void updateLabAssignmentMarks(Student student, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getMarksObj(course);

        marksObj.updateLabAssignment(marks);
    }

    public void updateMidMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getMarksObj(course);

        marksObj.updateMid(num, marks);
    }

    public void updateTerminalMarks(Student student, int num, double marks) {
        Semester semester = student.getLastSemester();
        Marks marksObj = semester.getMarksObj(course);

        marksObj.updateTerminal(num, marks);
    }

}
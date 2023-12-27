import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Course implements Serializable {
    private String id;
    private String name;
    private int theoryHours;
    private int labHours;
    private ArrayList<Student> registeredStudents;

    Course() {
        registeredStudents = new ArrayList<>();
    }

    public Course(String id, String name, int theoryHours, int labHours) {
        this();
        setId(id);
        setName(name);
        setTheoryHours(theoryHours);
        setLabHours(labHours);
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public Student getStudent(String username) {
        for (Student s : registeredStudents) {
            if (s.getCredential().getUsername().compareTo(username) == 0) {
                return s;
            }
        }
        return null;
    }

    public void registerStudentInCourse(Student student) {
        this.registeredStudents.add(student);

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheoryHours(int theoryHours) {
        this.theoryHours = theoryHours;
    }

    public String getId() {
        return id;
    }

    public int getLabHours() {
        return labHours;
    }

    public String getName() {
        return name;
    }

    public int getTheoryHours() {
        return theoryHours;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " Name: " + this.getName() + "\nTheory Hours: " + this.getTheoryHours()
                + " Lab Hours: " + this.getLabHours() + "\n" + "Registered Students: " + this.getRegisteredStudents()
                + "\n";
    }
}
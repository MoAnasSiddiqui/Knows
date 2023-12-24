
public class Course {
    private String id;
    private String name;
    private int theoryHours;
    private int labHours;

    Course() {
    }

    public Course(String id, String name, int theoryHours, int labHours) {
        setId(id);
        setName(name);
        setTheoryHours(theoryHours);
        setLabHours(labHours);
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
                + " Lab Hours: " + this.getLabHours() + "\n\n";
    }
}
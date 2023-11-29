import java.util.ArrayList;

public class Course{
    public static String id;
    public static String name;
    public static int theoryHour;
    public static int labHour;
    public static Faculty professor;

    private ArrayList<Double> assignments;
    private ArrayList<Double> quizes;
    private ArrayList<Double> labAssignments;
    private double[] mids;
    private double[] terminals;

    Course(){}
}
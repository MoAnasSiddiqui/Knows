import java.util.ArrayList;

public class Marks {
  private Course course;
  private ArrayList<Double> assignments;
  private ArrayList<Double> quizes;
  private ArrayList<Double> labAssignments;
  private double[] mids;
  private double[] terminals;

  public Marks() {
  }

  public Marks(Course course) {
    this.course = course;
  }

  public void addAssignment(double marks) {
  }

  public void addQuiz(double marks) {
  }

  public void addLabAssignment(double marks) {
  }

  public void updateAssignment(int place, double marks) {
  }

  public void updateQuiz(int place, double marks) {
  }

  public void updateLabAssignment(int place, double marks) {
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
}

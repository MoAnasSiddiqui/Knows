import java.io.Serializable;

public class Marks implements Serializable {
  private Course course;
  private double[] assignments;
  private double[] quizes;
  private double[] labAssignments;
  private double[] mids;
  private double[] terminals;

  public Marks() {
    assignments = new double[] { -1, -1, -1, -1 };
    quizes = new double[] { -1, -1, -1, -1 };
    labAssignments = new double[] { 0, 0 };
    mids = new double[] { -1, -1 };
    terminals = new double[] { -1, -1 };
  }

  public Marks(Course course) {
    this();
    this.course = course;
  }

  public void updateAssignment(int place, double marks) {
    assignments[place - 1] = marks;
  }

  public void updateQuiz(int place, double marks) {
    quizes[place - 1] = marks;
  }

  public void updateLabAssignment(double marks) {
    labAssignments[0] += marks;
    labAssignments[1]++;
  }

  public void updateMid(int place, double marks) {
    mids[place] = marks;
  }

  public void updateTerminal(int place, double marks) {
    terminals[place] = marks;
  }

  public Course getCourse() {
    return course;
  }

  @Override
  public String toString() {
    String message = course.toString();
    message = message + "Theory Details:\n\n";
    message += "Assignments: \n";
    for (int i = 0; i < 4; i++) {
      if (assignments[i] != -1) {
        message = message + "Assignment " + (i + 1) + ": " + assignments[i] + "\n";
      } else {
        message = message + "Assignment " + (i + 1) + ": null" + "\n";
      }
    }
    message = message + "\nQuizes: \n";
    for (int i = 0; i < 4; i++) {
      if (quizes[i] != -1) {
        message = message + "Quiz " + (i + 1) + ": " + quizes[i] + "\n";
      } else {
        message = message + "Quiz " + (i + 1) + ": null" + "\n";
      }
    }
    if (mids[0] != -1) {
      message = message + "\nTheory Mid: " + mids[0] + "\n";
    } else {
      message = message + "\nTheory Mid: null\n";
    }
    if (terminals[0] != -1) {
      message = message + "\nTheory Terminal: " + terminals[0] + "\n";
    } else {
      message = message + "\nTheory Terminal: null\n";
    }
    if (this.course.getLabHours() != 0) {
      message = message + "\nLab Details:\n\n";
      if (labAssignments[1] != 0) {
        message += "Lab Assignments: " + (labAssignments[0] / labAssignments[1]) + "\n";
      } else {
        message += "Lab Assignments: null\n";
      }
      if (mids[1] != -1) {
        message += "Lab Mid: " + mids[1] + "\n";
      } else {
        message += "Lab Mid: null\n";
      }
      if (terminals[1] != -1) {
        message += "Lab Terminal: " + terminals[1] + "\n";
      } else {
        message += "Lab Terminal: null\n";
      }
    }

    return message;
  }

}

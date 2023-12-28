import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KnowsGUI {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JComboBox<String> userTypeComboBox;
    private static Admin[] admins = { new Admin("a", "a", "a") };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeGUI();
            showMainPage();
        });
    }

    private static void initializeGUI() {
        frame = new JFrame("KNOWS");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void showMainPage() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for each user type
        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(e -> showAdminPage());

        JButton facultyButton = new JButton("Faculty");
        facultyButton.addActionListener(e -> showFacultyPage());

        JButton studentButton = new JButton("Student");
        studentButton.addActionListener(e -> showStudentPage());

        // Add buttons to the main panel
        mainPanel.add(adminButton);
        mainPanel.add(facultyButton);
        mainPanel.add(studentButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showAdminPage() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Admin actions
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> showAdminLogin());

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showAdminLogin() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for username and password
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Create a button for login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Check the admin credentials
            boolean isAdminAuthenticated = false;
            for (Admin admin : admins) {
                if (username.equals(admin.getCredential().getUsername())
                        && admin.getCredential().validatePin(password)) {
                    isAdminAuthenticated = true;
                    break;
                }
            }

            if (isAdminAuthenticated) {
                showMessageDialog("Admin login successful");
                adminPortal();
            } else {
                showMessageDialog("Incorrect username or password. Please try again.");
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add components to the main panel
        mainPanel.add(new JLabel("Admin Login"));
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void adminPortal() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Admin portal actions
        JButton registerStudentButton = new JButton("Register Student");
        registerStudentButton.addActionListener(e -> showStudentRegistration());

        JButton registerFacultyButton = new JButton("Register Faculty Member");
        registerFacultyButton.addActionListener(e -> showFacultyRegistration());

        JButton registerKNOWSCourseButton = new JButton("Register Course for KNOWS");
        registerKNOWSCourseButton.addActionListener(e -> showKNOWSCourseRegistration());

        JButton registerStudentCourseButton = new JButton("Register Course for Student");
        registerStudentCourseButton.addActionListener(e -> showStudentCourseRegistration());

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(registerStudentButton);
        mainPanel.add(registerFacultyButton);
        mainPanel.add(registerKNOWSCourseButton);
        mainPanel.add(registerStudentCourseButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showStudentRegistration() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for student registration
        JTextField nameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Create a button for student registration
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Validate input fields
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showMessageDialog("Please fill in all the fields.");
            } else {
                // Create a new Student object and register it
                Admin.registerStudent(username, password, name);

                showMessageDialog("Student successfully registered: " + name);

                // Optionally, you can clear the input fields after registration
                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> adminPortal());

        // Add components to the main panel
        mainPanel.add(new JLabel("Student Registration"));
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(backButton);
        mainPanel.add(registerButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showFacultyRegistration() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for faculty registration
        JTextField nameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Get the list of courses
        ArrayList<Course> courses = Admin.getCourses();
        String[] courseNames = courses.stream().map(Course::getName).toArray(String[]::new);
        JComboBox<String> courseComboBox = new JComboBox<>(courseNames);

        // Create a button for faculty registration
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Validate input fields
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showMessageDialog("Please fill in all the fields.");
            } else {
                // Get the selected course
                int selectedCourseIndex = courseComboBox.getSelectedIndex();
                if (selectedCourseIndex == -1) {
                    showMessageDialog("Please choose a course.");
                } else {
                    Course facultyCourse = courses.get(selectedCourseIndex);

                    // Create a new Faculty object and register it
                    Admin.registerFaculty(username, password, name, facultyCourse);

                    showMessageDialog("Faculty member successfully registered: " + name);

                    // Optionally, you can clear the input fields after registration
                    nameField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> adminPortal());

        // Add components to the main panel
        mainPanel.add(new JLabel("Faculty Registration"));
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel("Choose Course:"));
        mainPanel.add(courseComboBox);
        mainPanel.add(backButton);
        mainPanel.add(registerButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showKNOWSCourseRegistration() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for course registration
        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField theoryHoursField = new JTextField(5);
        JTextField labHoursField = new JTextField(5);

        // Create a button for course registration
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();

            // Validate input fields
            if (name.isEmpty() || id.isEmpty()) {
                showMessageDialog("Please fill in all the fields.");
            } else {
                // Parse theory and lab hours from text fields
                int theoryHours;
                int labHours;
                try {
                    theoryHours = Integer.parseInt(theoryHoursField.getText());
                    labHours = Integer.parseInt(labHoursField.getText());
                } catch (NumberFormatException ex) {
                    showMessageDialog("Invalid theory or lab hours. Please enter valid integers.");
                    return;
                }

                // Create a new Course object and register it for KNOWS
                Admin.registerKNOWSCourse(id, name, theoryHours, labHours);

                showMessageDialog("Course successfully registered: " + name);

                // Optionally, you can clear the input fields after registration
                nameField.setText("");
                idField.setText("");
                theoryHoursField.setText("");
                labHoursField.setText("");
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> adminPortal());

        // Add components to the main panel
        mainPanel.add(new JLabel("Course Registration for KNOWS"));
        mainPanel.add(new JLabel("Course Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Course ID:"));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Theory Hours:"));
        mainPanel.add(theoryHoursField);
        mainPanel.add(new JLabel("Lab Hours:"));
        mainPanel.add(labHoursField);
        mainPanel.add(registerButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showStudentCourseRegistration() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create a combo box for choosing a student
        ArrayList<Student> students = Admin.getStudents();
        String[] studentUsernames = students.stream().map(s -> s.getCredential().getUsername()).toArray(String[]::new);
        JComboBox<String> studentComboBox = new JComboBox<>(studentUsernames);

        // Create a combo box for choosing a course
        ArrayList<Course> courses = Admin.getCourses();
        String[] courseNames = courses.stream().map(Course::getName).toArray(String[]::new);
        JComboBox<String> courseComboBox = new JComboBox<>(courseNames);

        // Create a button for course registration for a student
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            // Get the selected student and course
            int selectedStudentIndex = studentComboBox.getSelectedIndex();
            int selectedCourseIndex = courseComboBox.getSelectedIndex();

            if (selectedStudentIndex == -1 || selectedCourseIndex == -1) {
                showMessageDialog("Please choose a student and a course.");
            } else {
                Student selectedStudent = students.get(selectedStudentIndex);
                Course selectedCourse = courses.get(selectedCourseIndex);

                // Check if the student is already registered for the course
                if (selectedCourse.getRegisteredStudents().contains(selectedStudent)) {
                    showMessageDialog("Student is already registered for the selected course.");
                } else {
                    ArrayList<Course> selectedCourseList = new ArrayList<>();
                    selectedCourseList.add(selectedCourse);
                    Admin.registerStudentCourses(selectedStudent, selectedCourseList);

                    showMessageDialog("Student successfully registered for the course.");

                    // Optionally, you can clear the selected items in the combo boxes
                    studentComboBox.setSelectedIndex(-1);
                    courseComboBox.setSelectedIndex(-1);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> adminPortal());

        // Add components to the main panel
        mainPanel.add(new JLabel("Student Course Registration"));
        mainPanel.add(new JLabel("Choose Student:"));
        mainPanel.add(studentComboBox);
        mainPanel.add(new JLabel("Choose Course:"));
        mainPanel.add(courseComboBox);
        mainPanel.add(registerButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showFacultyPage() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Admin actions
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> showFacultyLogin());

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showFacultyLogin() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for username and password
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Create a button for login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            Faculty faculty = new Faculty();

            // Check the admin credentials
            boolean isAuthenticated = false;
            for (Faculty f : Admin.getFaculty()) {
                if (username.equals(f.getCredential().getUsername())
                        && f.getCredential().validatePin(password)) {
                    isAuthenticated = true;
                    faculty = f;
                    break;
                }
            }

            if (isAuthenticated) {
                showMessageDialog("Faculty login successful");
                facultyPortal(faculty);
            } else {
                showMessageDialog("Incorrect username or password. Please try again.");
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add components to the main panel
        mainPanel.add(new JLabel("Admin Login"));
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void facultyPortal(Faculty faculty) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Faculty actions
        JButton viewDetailsButton = new JButton("View Faculty Details");
        viewDetailsButton.addActionListener(e -> showFacultyDetails(faculty));

        JButton addMarksButton = new JButton("Add Marks");
        addMarksButton.addActionListener(e -> showAddMarks(faculty));

        // Create a "Logout" button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(new JLabel("Faculty Portal"));
        mainPanel.add(viewDetailsButton);
        mainPanel.add(addMarksButton);
        mainPanel.add(logoutButton); // Adding the "Logout" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showFacultyDetails(Faculty faculty) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create labels to display faculty details
        JLabel nameLabel = new JLabel("Name: " + faculty.getName());
        JLabel usernameLabel = new JLabel("Username: " + faculty.getCredential().getUsername());

        Course facultyCourse = faculty.getCourse();
        JLabel courseLabel = new JLabel(
                "Course: " + (facultyCourse != null ? facultyCourse.getName() : "Not assigned"));

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> facultyPortal(faculty));

        // Add labels and buttons to the main panel
        mainPanel.add(new JLabel("Faculty Details"));
        mainPanel.add(nameLabel);
        mainPanel.add(usernameLabel);
        mainPanel.add(courseLabel);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showAddMarks(Faculty faculty) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Display a combo box with student usernames
        ArrayList<Student> registeredStudents = faculty.getCourse().getRegisteredStudents();
        String[] studentUsernames = registeredStudents.stream().map(student -> student.getCredential().getUsername())
                .toArray(String[]::new);
        JComboBox<String> studentComboBox = new JComboBox<>(studentUsernames);

        // Create a "Select" button
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedStudentIndex = studentComboBox.getSelectedIndex();
                if (selectedStudentIndex != -1) {
                    Student selectedStudent = registeredStudents.get(selectedStudentIndex);
                    showMarksOptions(faculty, selectedStudent);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> facultyPortal(faculty));

        // Add components to the main panel
        mainPanel.add(new JLabel("Select a student:"));
        mainPanel.add(studentComboBox);
        mainPanel.add(selectButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showMarksOptions(Faculty faculty, Student selectedStudent) {
        // Create buttons for adding various types of marks
        JButton addAssignmentMarksButton = new JButton("Add Assignment Marks");
        addAssignmentMarksButton.addActionListener(e -> addAssignmentMarks(faculty, selectedStudent));

        JButton addQuizMarksButton = new JButton("Add Quiz Marks");
        addQuizMarksButton.addActionListener(e -> addQuizMarks(faculty, selectedStudent));

        JButton addLabAssignmentMarksButton = new JButton("Add Lab Assignment Marks");
        addLabAssignmentMarksButton.addActionListener(e -> addLabAssignmentMarks(faculty, selectedStudent));

        JButton addMidMarksButton = new JButton("Add Mid Marks");
        addMidMarksButton.addActionListener(e -> addMidMarks(faculty, selectedStudent));

        JButton addTerminalMarksButton = new JButton("Add Terminal Marks");
        addTerminalMarksButton.addActionListener(e -> addTerminalMarks(faculty, selectedStudent));

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> facultyPortal(faculty));

        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Add buttons to the main panel
        mainPanel.add(new JLabel("Add Marks for " + selectedStudent.getName()));
        mainPanel.add(addAssignmentMarksButton);
        mainPanel.add(addQuizMarksButton);
        mainPanel.add(addLabAssignmentMarksButton);
        mainPanel.add(addMidMarksButton);
        mainPanel.add(addTerminalMarksButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    // The other add*Marks methods would also need to be modified to accept the
    // selected student as a parameter

    private static void addAssignmentMarks(Faculty faculty, Student selectedStudent) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for assignment details
        JTextField assignmentNumberField = new JTextField(5);
        JTextField marksField = new JTextField(5);

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int assignmentNumber = Integer.parseInt(assignmentNumberField.getText());
                    double marks = Double.parseDouble(marksField.getText());

                    // Call a method to add assignment marks to the student object
                    faculty.updateAssignmentMarks(selectedStudent, assignmentNumber, marks);

                    // Show a success message
                    JOptionPane.showMessageDialog(frame, "Assignment marks added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Go back to the marks options
                    showMarksOptions(faculty, selectedStudent);
                } catch (NumberFormatException ex) {
                    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMarksOptions(faculty, selectedStudent));

        // Add components to the main panel
        mainPanel.add(new JLabel("Add Assignment Marks for " + selectedStudent.getName()));
        mainPanel.add(new JLabel("Assignment Number:"));
        mainPanel.add(assignmentNumberField);
        mainPanel.add(new JLabel("Marks:"));
        mainPanel.add(marksField);
        mainPanel.add(submitButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void addQuizMarks(Faculty faculty, Student selectedStudent) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for quiz details
        JTextField quizNumberField = new JTextField(5);
        JTextField marksField = new JTextField(5);

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quizNumber = Integer.parseInt(quizNumberField.getText());
                    double marks = Double.parseDouble(marksField.getText());

                    // Call a method to add quiz marks to the student object
                    faculty.updateQuizMarks(selectedStudent, quizNumber, marks);

                    // Show a success message
                    JOptionPane.showMessageDialog(frame, "Quiz marks added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Go back to the marks options
                    showMarksOptions(faculty, selectedStudent);
                } catch (NumberFormatException ex) {
                    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMarksOptions(faculty, selectedStudent));

        // Add components to the main panel
        mainPanel.add(new JLabel("Add Quiz Marks for " + selectedStudent.getName()));
        mainPanel.add(new JLabel("Quiz Number:"));
        mainPanel.add(quizNumberField);
        mainPanel.add(new JLabel("Marks:"));
        mainPanel.add(marksField);
        mainPanel.add(submitButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void addLabAssignmentMarks(Faculty faculty, Student selectedStudent) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create an input field for lab assignment marks
        JTextField marksField = new JTextField(5);

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double marks = Double.parseDouble(marksField.getText());

                    // Call a method to add lab assignment marks to the student object
                    faculty.updateLabAssignmentMarks(selectedStudent, marks);

                    // Show a success message
                    JOptionPane.showMessageDialog(frame, "Lab Assignment marks added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Go back to the marks options
                    showMarksOptions(faculty, selectedStudent);
                } catch (NumberFormatException ex) {
                    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMarksOptions(faculty, selectedStudent));

        // Add components to the main panel
        mainPanel.add(new JLabel("Add Lab Assignment Marks for " + selectedStudent.getName()));
        mainPanel.add(new JLabel("Marks:"));
        mainPanel.add(marksField);
        mainPanel.add(submitButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void addMidMarks(Faculty faculty, Student selectedStudent) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for mid details
        JTextField marksField = new JTextField(5);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[] { "Theory", "Lab" });

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double marks = Double.parseDouble(marksField.getText());
                    String type = (String) typeComboBox.getSelectedItem();

                    // Call a method to add mid marks to the student object
                    faculty.updateMidMarks(selectedStudent, type.equals("Lab") ? 1 : 0, marks);

                    // Show a success message
                    JOptionPane.showMessageDialog(frame, "Mid marks added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Go back to the marks options
                    showMarksOptions(faculty, selectedStudent);
                } catch (NumberFormatException ex) {
                    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMarksOptions(faculty, selectedStudent));

        // Add components to the main panel
        mainPanel.add(new JLabel("Add Mid Marks for " + selectedStudent.getName()));
        mainPanel.add(new JLabel("Type:"));
        mainPanel.add(typeComboBox);
        mainPanel.add(new JLabel("Marks:"));
        mainPanel.add(marksField);
        mainPanel.add(submitButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void addTerminalMarks(Faculty faculty, Student selectedStudent) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for terminal details
        JTextField marksField = new JTextField(5);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[] { "Theory", "Lab" });

        // Create a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double marks = Double.parseDouble(marksField.getText());
                    String type = (String) typeComboBox.getSelectedItem();

                    // Call a method to add terminal marks to the student object
                    faculty.updateTerminalMarks(selectedStudent, type.equals("Lab") ? 1 : 0, marks);

                    // Show a success message
                    JOptionPane.showMessageDialog(frame, "Terminal marks added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Go back to the marks options
                    showMarksOptions(faculty, selectedStudent);
                } catch (NumberFormatException ex) {
                    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMarksOptions(faculty, selectedStudent));

        // Add components to the main panel
        mainPanel.add(new JLabel("Add Terminal Marks for " + selectedStudent.getName()));
        mainPanel.add(new JLabel("Type:"));
        mainPanel.add(typeComboBox);
        mainPanel.add(new JLabel("Marks:"));
        mainPanel.add(marksField);
        mainPanel.add(submitButton);
        mainPanel.add(backButton); // Adding the "Back" button

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showStudentPage() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Admin actions
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> showStudentLogin());

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showStudentLogin() {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create input fields for username and password
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Create a button for login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            Student student = new Student();

            // Check the admin credentials
            boolean isAuthenticated = false;
            for (Student s : Admin.getStudents()) {
                if (username.equals(s.getCredential().getUsername())
                        && s.getCredential().validatePin(password)) {
                    isAuthenticated = true;
                    student = s;
                    break;
                }
            }

            if (isAuthenticated) {
                showMessageDialog("Student login successful");
                studentPortal(student);
            } else {
                showMessageDialog("Incorrect username or password. Please try again.");
            }
        });

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add components to the main panel
        mainPanel.add(new JLabel("Admin Login"));
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void studentPortal(Student student) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Create buttons for Student actions
        JButton viewDetailsButton = new JButton("View Student Details");
        viewDetailsButton.addActionListener(e -> showStudentDetails(student));

        JButton courseSummaryButton = new JButton("Course Summary");
        courseSummaryButton.addActionListener(e -> semesterSummary(student));

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainPage());

        // Add buttons to the main panel
        mainPanel.add(viewDetailsButton);
        mainPanel.add(courseSummaryButton);
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showStudentDetails(Student student) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Display student details
        mainPanel.add(new JLabel("Student Details"));
        mainPanel.add(new JLabel("Name: " + student.getName()));
        mainPanel.add(new JLabel("Username: " + student.getCredential().getUsername()));

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> studentPortal(student));

        // Add the "Back" button to the main panel
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void semesterSummary(Student student) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Display all registered courses for the student
        ArrayList<Marks> registeredCourses = student.getLastSemester().getRegisteredCourseMarks();
        if (registeredCourses.isEmpty()) {
            mainPanel.add(new JLabel("No registered courses for the current semester."));
        } else {
            mainPanel.add(new JLabel("Select a course to view the summary:"));

            // Create a combo box with course names
            JComboBox<String> courseComboBox = new JComboBox<>();
            for (Marks marks : registeredCourses) {
                courseComboBox.addItem(marks.getCourse().getName());
            }
            mainPanel.add(courseComboBox);

            // Create a "Submit" button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the selected course name
                    String selectedCourseName = (String) courseComboBox.getSelectedItem();

                    // Find the corresponding course marks object
                    Marks selectedCourseMarks = null;
                    for (Marks marks : registeredCourses) {
                        if (marks.getCourse().getName().equals(selectedCourseName)) {
                            selectedCourseMarks = marks;
                            break;
                        }
                    }

                    // Display the summary for the selected course
                    if (selectedCourseMarks != null) {
                        showCourseSummary(student, selectedCourseMarks);
                    }
                }
            });
            mainPanel.add(submitButton);
        }

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> studentPortal(student));

        // Add the "Back" button to the main panel
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    private static void showCourseSummary(Student student, Marks courseMarks) {
        // Clear existing components from the main panel
        mainPanel.removeAll();

        // Set GridLayout with rows for each type of marks
        mainPanel.setLayout(new GridLayout(0, 1));

        // Display course summary
        mainPanel.add(new JLabel("Course Summary for " + courseMarks.getCourse().getName()));

        // Display Assignments
        mainPanel.add(new JLabel("Assignments:"));
        double[] assignmentMarks = courseMarks.getAssignments();
        for (int i = 0; i < 4; i++) {
            mainPanel.add(new JLabel(
                    "Assignment " + (i + 1) + ": " + (assignmentMarks[i] != -1.0 ? assignmentMarks[i] : "N/A")));
        }

        // Display Quizzes
        mainPanel.add(new JLabel("Quizzes:"));
        double[] quizMarks = courseMarks.getQuizes();
        for (int i = 0; i < 4; i++) {
            mainPanel.add(new JLabel("Quiz " + (i + 1) + ": " + (quizMarks[i] != -1.0 ? quizMarks[i] : "N/A")));
        }

        // Display Midterms
        mainPanel.add(new JLabel("Midterms:"));
        double[] midtermMarks = courseMarks.getMids();
        for (int i = 0; i < 2; i++) {
            mainPanel
                    .add(new JLabel("Midterm " + (i + 1) + ": " + (midtermMarks[i] != -1.0 ? midtermMarks[i] : "N/A")));
        }

        // Display Terminals
        mainPanel.add(new JLabel("Terminals:"));
        double[] terminalMarks = courseMarks.getTerminals();
        for (int i = 0; i < 2; i++) {
            mainPanel.add(
                    new JLabel("Terminal " + (i + 1) + ": " + (terminalMarks[i] != -1.0 ? terminalMarks[i] : "N/A")));
        }

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> semesterSummary(student));

        // Add the "Back" button to the main panel
        mainPanel.add(backButton);

        // Refresh the frame to reflect the changes
        frame.revalidate();
        frame.repaint();
    }

    // Add more GUI methods as needed

    private static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private static String showInputDialog(String message) {
        return JOptionPane.showInputDialog(frame, message);
    }
}

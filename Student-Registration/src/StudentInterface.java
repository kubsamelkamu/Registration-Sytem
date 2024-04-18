import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and display the search interface for student info
                //createStudentInterface();
                createSearchInterface();
            }
        });
    }

    public static String searchStudentById(String studentId) {

        
        String DB_URL = "jdbc:mysql://localhost:3306/course_db"; // Adjust URL for your XAMPP setup
        String DB_USERNAME = "root"; // Replace with your XAMPP username
        String DB_PASSWORD = ""; // Replace with your XAMPP password




        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder results = new StringBuilder();

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Execute a query to retrieve student data by ID

            stmt = conn.createStatement();
            String sql = "SELECT * FROM student_reg_table WHERE stud_id = " + studentId;
            rs = stmt.executeQuery(sql);

            // Check if any results were found
            
            if (rs.next()) {
                results.append("Student ID: " + rs.getInt("stud_id") + "\n");

                // Loop through all columns and add them to the results string
               /*  for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    results.append(columnName + ": " + columnValue + "\n");

                  
                }*/

                String first_column = rs.getMetaData().getColumnName(1);
                String second_column = rs.getMetaData().getColumnName(2);
                String third_column = rs.getMetaData().getColumnName(3);
                String fourth_column = rs.getMetaData().getColumnName(4);
                String fifth_column = rs.getMetaData().getColumnName(5);
                String six_column = rs.getMetaData().getColumnName(5);

                // store values of each column in the database table

                String firstName = rs.getString(first_column);
                String middleName = rs.getString(second_column);
                String lastName = rs.getString(third_column);
                String stud_id = rs.getString(fourth_column);
                String stud_dep = rs.getString(fifth_column);
                String stud_contact = rs.getString(six_column);

                StudentInterface.createStudentInterface(firstName,middleName,lastName,stud_id,stud_dep,stud_contact );

            } else {
                results.append("Student with ID " + studentId + " not found.");
            }
        } catch (SQLException se) {
            // Handle JDBC errors
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

            
        }

       
        return results.toString();
    }

    public static void createSearchInterface()  {

        
        JFrame searchFrame = new JFrame("Search Student Information");
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setSize(250, 300);
        searchFrame.setResizable(false);
        searchFrame.setLayout(new BorderLayout());
        searchFrame.setLocationRelativeTo(null); // Center the frame on the screen

        JLabel label = new JLabel("Enter Student ID:");
        JTextField textField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(textField);
        panel.add(searchButton);

        searchFrame.add(panel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String studentID = textField.getText();

                if (studentID.isEmpty()){

                    // Perform search operation here (database operation)
                    // For now, let's just display a message dialog
                    JOptionPane.showMessageDialog(searchFrame, "Student with ID: " + studentID + " not found.");
                } else {

                
               
                try{

                    Connection conn;
                    Statement statement;
                    
                    final String JDBC_URL = "jdbc:mysql://localhost:3306/course_db";
                    final String USERNAME = "root";
                    final String PASSWORD = "";
            
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                    statement = conn.createStatement();
                    System.out.println("Connected to the database.");
        
                    String stud_id = textField.getText();
                    String  stored_value  =  StudentInterface.searchStudentById(stud_id);
                    System.out.println(stored_value);

                   
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
   
            }
         }

        });

        searchFrame.setVisible(true);
    }

    public static void createStudentInterface(String fn, String mn, String ln,String stud_dep,String contact,String stud_id) {

       

        JFrame studentFrame = new JFrame("Student Information");
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentFrame.setSize(350, 400);
        studentFrame.setResizable(false);
        studentFrame.setLayout(new GridLayout(7, 2));
        studentFrame.setLocationRelativeTo(null); // Center the frame on the screen

        JLabel firstNameLabel = new JLabel("    First Name:");
        JLabel firstNameField = new JLabel();
        firstNameField.setText(fn);

        JLabel middleNameLabel = new JLabel("    Middle Name:");
        JLabel middleNameField = new JLabel();
        middleNameField.setText(mn);

        JLabel lastNameLabel = new JLabel("     Last Name:");
        JLabel lastNameField = new JLabel();
        lastNameField.setText(ln);
        
        JLabel studentIDLabel = new JLabel("     Student ID:");
        JLabel studentIDField = new JLabel();
        studentIDField.setText(stud_id);

        JLabel departmentLabel = new JLabel("    Department:");
        JLabel departmentField = new JLabel();
        departmentField.setText(stud_dep);

        JLabel contactLabel = new JLabel("    Contact:");
        JLabel contactField = new JLabel();
        contactField.setText(contact);

       

        studentFrame.add(firstNameLabel);
        studentFrame.add(firstNameField);
        studentFrame.add(middleNameLabel);
        studentFrame.add(middleNameField);
        studentFrame.add(lastNameLabel);
        studentFrame.add(lastNameField);
        studentFrame.add(studentIDLabel);
        studentFrame.add(studentIDField);
        studentFrame.add(departmentLabel);
        studentFrame.add(departmentField);
        studentFrame.add(contactLabel);
        studentFrame.add(contactField);

        studentFrame.setVisible(true);
    }
}

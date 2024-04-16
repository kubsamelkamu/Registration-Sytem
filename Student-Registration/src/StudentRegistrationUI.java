import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistrationUI extends JFrame {

    private JLabel titleLabel, firstNameLabel, middleNameLabel, lastNameLabel, studIdLabel, studDepLabel, studContactLabel;
    private JTextField firstNameField, middleNameField, lastNameField, studIdField, studDepField, studContactField;
    protected JButton registerButton;

    public StudentRegistrationUI(String string) {
        super();

      // Create labels and text fields
        titleLabel = new JLabel("      Student Registration Form   " , JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));  // Title formatting
         
        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15); 
        middleNameLabel = new JLabel("Middle Name:");
        middleNameField = new JTextField(15);
        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);
        studIdLabel = new JLabel("Student ID:");
        studIdField = new JTextField(15);
        studDepLabel = new JLabel("Department:");
        studDepField = new JTextField(15);
        studContactLabel = new JLabel("Contact Number:");
        studContactField = new JTextField(15); 
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registerButton.setForeground(Color.BLUE);
        
        // Arrange components using GridBagLayout
        getContentPane().setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints();


        // Get the content pane of the JFrame and set its background color
        getContentPane().setBackground(Color.lightGray);
        
        c.fill = GridBagConstraints.HORIZONTAL;  // Text fields fill available space
        c.insets = new Insets(5, 5, 5, 5);  // Padding between components

        // Title label
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 3;  // Span across all three columns
        add(titleLabel, c);
        c.gridwidth = 1;  // Reset for regular components

        // First row
        c.gridy = 1;
        c.gridx = 0;
        add(firstNameLabel, c);
        c.gridx = 1;
        add(middleNameLabel, c);
        c.gridx = 2;
        add(lastNameLabel, c);

        // Second row
        c.gridy = 2;
        c.gridx = 0;
        add(firstNameField, c);
        c.gridx = 1;
        add(middleNameField, c);
        c.gridx = 2;
        add(lastNameField, c);

        // Third row
        c.gridy = 3;
        c.gridx = 0;
        add(studIdLabel, c);
        c.gridx = 1;
        add(studDepLabel, c);
        c.gridx = 2;
        add(studContactLabel, c);
     

        // Fourth row
        c.gridy = 4;
        c.gridx = 0;
        add(studIdField, c);
        c.gridx = 1;
        add(studDepField, c);
        c.gridx = 2;
        add(studContactField, c);
        c.gridy = 5;
        c.gridx = 1;
        add(registerButton,c);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();  
                String lastName = lastNameField.getText();
                String stud_id = studIdField.getText();
                String stud_dep = studDepField.getText();
                String stud_contact = studContactField.getText();

                if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || stud_dep.isEmpty() || stud_contact.isEmpty() || stud_id.isEmpty()) {
                
                    JOptionPane.showInputDialog(this, "Please fill in all fields.");
                    return;
                }
                
            // calling the insertData method to insert data into database
            String[] columns = {"firstName", "middleName", "lastName","stud_id","stud_dep","stud_contact"};
            Object[] values = {firstName, middleName, lastName,stud_id,stud_dep,stud_contact};

            try {
                insertData("student_reg_table", columns, values);
            } catch (SQLException e1) {
                System.out.println("an eror occurred : "+ e1.getMessage());
            }
                
            }
            
        });

        // Set frame properties
        setSize(800, 400);   // Adjusted size for 3 rows and padding
        setLocationRelativeTo(null);    // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);      
    }

    public  void insertData(String student_reg_table, String[] columnNames, Object[] data) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/course_db";
        String username = "root";
        String password = "";
      
        Connection connection = null;
        PreparedStatement preparedStatement = null;
      
        try {

          // Load the MySQL driver
          Class.forName("com.mysql.cj.jdbc.Driver");
      
          // Establish a connection
          connection = DriverManager.getConnection(url, username, password);
      
          StringBuilder sql = new StringBuilder("INSERT INTO " + student_reg_table + " (");
          for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]);
            if (i < columnNames.length - 1) {
              sql.append(", ");
            } else {
              sql.append(") VALUES (");
            }
          }
          for (int i = 0; i < data.length; i++) {
            sql.append("?");
            if (i < data.length - 1) {
              sql.append(", ");
            } else {
              sql.append(")");
            }
          }
    
          preparedStatement = connection.prepareStatement(sql.toString());
      
          for (int i = 0; i < data.length; i++) {
            preparedStatement.setObject(i + 1, data[i]);
          }
          // Execute the insert query
          preparedStatement.executeUpdate();
          JOptionPane.showMessageDialog(null, "Register Student successfully");
          //System.out.println("Data inserted successfully!");

          // clear input text Feilds

          firstNameField.setText("");
          middleNameField.setText("");
          lastNameField.setText("");
          studIdField.setText("");
          studDepField.setText("");
          studContactField.setText("");
      
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } finally {
          // Close resources (connection and statement)
          if (preparedStatement != null) {
            preparedStatement.close();
          }
          if (connection != null) {
            connection.close();
          }
        }
      }
      
    public static void main(String[] args) {
        new StudentRegistrationUI("Student Registration");
    }
}

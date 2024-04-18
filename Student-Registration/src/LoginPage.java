import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginPage extends JFrame{

    public LoginPage(){

        setTitle("WEL-COME PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // created components
        JLabel label = new JLabel("LOGIN PAGE ",JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD,30));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 45));


        getContentPane().setBackground(Color.LIGHT_GRAY);
             
        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(150, 50));
        adminButton.setFont(new Font("Arial", Font.PLAIN, 25));

        adminButton.addActionListener(new ActionListener() {

            @Override 
            public void actionPerformed(ActionEvent e) {

               JFrame  adminloginFrame = new JFrame("ADMIN LOGIN");
               adminloginFrame.setSize(200,500);
               JPanel panel = new JPanel();
               JLabel label = new JLabel("Enter Admin Password: ");
               JTextField passwordFeild = new JTextField(20);
               JButton loginButton  = new JButton("LOGIN");

            // adding action listenner to the login Button
               loginButton.addActionListener(new ActionListener() {
                int attempts = 0;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                  String enteredpassword = new String(passwordFeild.getText());
                  if (enteredpassword.equals("admin123")) {
                      // close prevoius admin-frame 
                      adminloginFrame.dispose();
                      //new StudentRegistrationUI("admin123");
                  }else{

                    // Password is incorrect
                    attempts++;
                    if (attempts < 3) {
                        JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
                        // Clear the password field
                        passwordFeild.setText("");
                    } else {

                       // After 3 failed attempts, deactivate the text field
                        passwordFeild.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "You have exceeded the maximum number of attempts. Please try again later.");
                        new LoginPage().setVisible(true);

                        // close admin login frame
                        adminloginFrame.dispose();
                        
                    }
                  }
                }
                
               });
               
               panel.add(label);
               panel.add(passwordFeild);
               panel.add(loginButton);

               adminloginFrame.getContentPane().add(panel);
               adminloginFrame.setSize(300,150);
               adminloginFrame.setLocationRelativeTo(null);
               adminloginFrame.setVisible(true);

               // close previous login window after the new admin frame is opened 
               dispose();
            }
            
        });

        JButton studButton = new JButton("Student");
        studButton.setPreferredSize(new Dimension(150, 50));
        studButton.setFont(new Font("Arial", Font.PLAIN, 25));

        // adding action listenner to the studButton 
        studButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //StudentInterface.createSearchInterface();
               dispose();
            }
        });

    // create panel for Buttons
        JPanel butJPanel = new JPanel();
        butJPanel.setLayout(new FlowLayout());
        butJPanel.add(adminButton);
        butJPanel.add(studButton);

        add(label,BorderLayout.CENTER);
        add(butJPanel,BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new LoginPage().setVisible(true);
    }   
}
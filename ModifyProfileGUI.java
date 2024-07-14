import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyProfileGUI extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private int cust_id;

    public ModifyProfileGUI(int cust_id) {
        this.cust_id = cust_id;
        setTitle("Modify Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label as a placeholder
        panel.add(submitButton);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProfile();
            }
        });

        // Load existing profile information
        loadProfile();
    }

    private void loadProfile() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DELIVERY_MANAGEMENT", "root", "Bhargav@2003");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE cust_id = ?")) {
            statement.setInt(1, cust_id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                phoneField.setText(resultSet.getString("phone"));
                // Password retrieval is optional, so it's not included here
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void modifyProfile() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(ModifyProfileGUI.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit method if any field is empty
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DELIVERY_MANAGEMENT", "root", "Bhargav@2003");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE customers SET first_name = ?, last_name = ?, phone = ?, password = ? WHERE cust_id = ?")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, password);
            statement.setInt(5, cust_id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(ModifyProfileGUI.this, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the modify profile window after successful update
            } else {
                JOptionPane.showMessageDialog(ModifyProfileGUI.this, "Failed to update profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ModifyProfileGUI.this, "Database error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifyProfileGUI modifyProfileGUI = new ModifyProfileGUI(0);
            modifyProfileGUI.setVisible(true);
        });
    }
}

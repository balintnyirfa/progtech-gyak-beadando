package Pages;

import classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import org.apache.log4j.*;

public class RegisterPage extends JDialog {
    final static Logger logger = Logger.getLogger(RegisterPage.class);
    public JTextField usernameText;
    public JTextField firstnameText;
    public JTextField lastnameText;
    public JTextField emailText;
    public JPasswordField passwordText;
    private JButton registerButton;
    private JPanel RegisterPanel;
    public JPasswordField passwordConfirm;
    private JButton backButton;
    public User user;

    public RegisterPage(JFrame parent) {
        super(parent);
        setTitle("Hozz létre egy új felhasználót!");
        setContentPane(RegisterPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Regisztráció gomb lenyomva.");
                registerUser();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignInPage signInPage = new SignInPage(null);
            }
        });
    }

    public void registerUser() {
        String Email = emailText.getText();
        String Username = usernameText.getText();
        String Firstname = firstnameText.getText();
        String Lastname = lastnameText.getText();
        String Password = String.valueOf(passwordText.getPassword());
        String ConfirmPassword = String.valueOf(passwordConfirm.getPassword());

        if (Email.isEmpty() || Username.isEmpty() || Firstname.isEmpty()
                || Lastname.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Password.equals(ConfirmPassword)) {
            JOptionPane.showMessageDialog(this, "A beírt két jelszó nem egyezik!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!Email.contains("@")){
            JOptionPane.showMessageDialog(this, "A beírt email nem valós!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO user(username, email, firstname, lastname, password) VALUES (?,?,?,?, md5(?))";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, Firstname);
            preparedStatement.setString(4, Lastname);
            preparedStatement.setString(5, Password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.setUsername(Username);
                user.setEmail(Email);
                user.setFirstname(Firstname);
                user.setLastname(Lastname);
                user.setPassword(Password);
            }
            stm.close();
            conn.close();
        } catch (Exception exception) {
            logger.error("Hiba történt: " + exception.getMessage());
        }


        if (user != null) {
            JOptionPane.showMessageDialog(this, "Sikeres regisztráció!");
            SignInPage signInPage = new SignInPage(null);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sikertelen regisztráció!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignInPage extends JDialog{
    private JTextField emailText;
    private JPasswordField passwordText;
    private JButton signInBtn;
    private JButton backButton;
    private JPanel signInPanel;

    public SignInPage (JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(signInPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailText.getText();
                String password = String.valueOf(passwordText.getPassword());
                
                user = getAuthenticatedUser(email, password);

                if (user != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(SignInPage.this,
                            "Email or Password Invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.getEmail();
                user.getPassword();
            }

            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        SignInPage signInPage = new SignInPage(null);
        User user = signInPage.user;

        if (user != null) {
            System.out.printf("Sikeres bejelentkezés! \n" +
                    "Email: %s\n" +
                    "Felhasználó név: %s", user.getEmail(), user.getUsername());
        }
        else {
            System.out.println("Bejelentkezés megszakítva!");
        }
    }
}

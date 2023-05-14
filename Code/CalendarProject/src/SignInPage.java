import javax.swing.*;
import javax.swing.plaf.PanelUI;
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
    private JPanel emailField;
    private JPanel passwordField;
    private JButton registerButton;
    private JLabel regLabel;

    public SignInPage (JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(signInPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        //emailText.setSize(20, 20);
        /*registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        registerButton.setBorderPainted(false);
        registerButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());*/


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


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterPage reg = new RegisterPage(null);
                reg.setVisible(true);
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
            String sql = "SELECT * FROM user WHERE email=? AND password=md5(?)";
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

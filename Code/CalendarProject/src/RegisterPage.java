import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class RegisterPage extends JDialog{
    private JTextField usernameText;
    private JTextField firstnameText;
    private JTextField lastnameText;
    private JTextField emailText;
    private JPasswordField passwordText;
    private JButton registerButton;
    private JPanel RegisterPanel;
    private JPasswordField passwordConfirm;
    private JButton backButton;
    public User user;

    public RegisterPage(JFrame parent){
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
                registerUser();
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
    private void registerUser(){
        String Email = emailText.getText();
        String Username = usernameText.getText();
        String Firstname = firstnameText.getText();
        String Lastname = lastnameText.getText();
        String Password = String.valueOf(passwordText.getPassword());
        String ConfirmPassword = String.valueOf(passwordConfirm.getPassword());

        if(Email.isEmpty() || Username.isEmpty() || Firstname.isEmpty()
                || Lastname.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()){
            JOptionPane.showMessageDialog(this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!Password.equals(ConfirmPassword)){
            JOptionPane.showMessageDialog(this, "A beírt két jelszó nem egyezik!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(Username, Email, Firstname, Lastname, Password);

        if(user != null){
            JOptionPane.showMessageDialog(this, "Sikeres regisztráció!");
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Sikertelen regisztráció!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private User addUserToDatabase(String username, String email, String firstname, String lastname, String password) {
        User user = null;

        return user;
    }

    public static void main(String[] args){
        RegisterPage reg = new RegisterPage(null);
    }
}

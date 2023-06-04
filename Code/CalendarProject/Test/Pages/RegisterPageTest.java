package Pages;

import junit.framework.TestCase;

public class RegisterPageTest extends TestCase {

    public void testRegisterUser_WithValidData() {
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailText.setText("something@something.com");
        registerPage.usernameText.setText("user");
        registerPage.firstnameText.setText("Something");
        registerPage.lastnameText.setText("Something");
        registerPage.passwordText.setText("password");
        registerPage.passwordConfirm.setText("password");

        registerPage.registerUser();

        assertNotNull(registerPage.user);
        assertEquals("user", registerPage.user.getUsername());
        assertEquals("something@something.com", registerPage.user.getEmail());
        assertEquals("Something", registerPage.user.getFirstname());
        assertEquals("Something", registerPage.user.getLastname());
        assertEquals("password", registerPage.user.getPassword());
    }

    public void testRegisterUser_WithMissingFields(){
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailText.setText("");
        registerPage.usernameText.setText("user");
        registerPage.firstnameText.setText("");
        registerPage.lastnameText.setText("Something");
        registerPage.passwordText.setText("password");
        registerPage.passwordConfirm.setText("password");

        registerPage.registerUser();

        assertNull(registerPage.user);
    }

    public void testRegisterUser_WithDifferentPassword(){
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailText.setText("something@something.com");
        registerPage.usernameText.setText("user");
        registerPage.firstnameText.setText("Something");
        registerPage.lastnameText.setText("Something");
        registerPage.passwordText.setText("password");
        registerPage.passwordConfirm.setText("password123");

        registerPage.registerUser();

        assertNull(registerPage.user);
    }

    //Nem tartalmaz @-jelet!
    public void testRegisterUser_WithWrongEmail(){
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailText.setText("user");
        registerPage.usernameText.setText("user");
        registerPage.firstnameText.setText("Something");
        registerPage.lastnameText.setText("Something");
        registerPage.passwordText.setText("password");
        registerPage.passwordConfirm.setText("password");

        registerPage.registerUser();

        assertNull(registerPage.user);
    }
}
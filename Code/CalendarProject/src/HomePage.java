import javax.swing.*;
import java.awt.*;

public class HomePage extends JDialog{
    private JButton ownCalendarButton;
    private JButton createNewCalendarButton;
    private JPanel homePanel;

    public HomePage(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        setVisible(true);
    }
}

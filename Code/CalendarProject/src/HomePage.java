import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JDialog{
    private JButton ownCalendarButton;
    private JButton createNewCalendarButton;
    private JPanel homePanel;

    public HomePage(JFrame parent){
        super(parent);
        setTitle("Kezdőoldal");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        ownCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createNewCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateCalendar createCalendar = new CreateCalendar(null);
                createCalendar.setVisible(true);
            }
        });

        setVisible(true);
    }
}
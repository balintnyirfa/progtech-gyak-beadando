package Pages;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JDialog{

    final static Logger logger = Logger.getLogger(HomePage.class);
    private JButton ownCalendarButton;
    private JButton createNewCalendarButton;
    private JPanel homePanel;
    private JButton logoutButton;

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
                dispose();
                ListCalendarsPage list = new ListCalendarsPage(null);
                list.setVisible(true);
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

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("A felhasználó kijelentkezett.");
                dispose();
            }
        });
    }
}

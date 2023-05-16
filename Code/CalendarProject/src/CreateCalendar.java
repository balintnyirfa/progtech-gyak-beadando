import javax.swing.*;
import java.awt.*;
//import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Properties;

public class CreateCalendar extends JDialog{

    private JPanel CreateCalendarPanel;
    private JTextField calendarTitle;
    private JComboBox<CalendarTypeEnum> calendarTypeComboBox;
    private JButton createCalendar_btn;

    //dátum kiválasztása és kezelése
    UtilDateModel model = new UtilDateModel();

    //naptár kiválasztó panel konfigurációja
    Properties properties = new Properties();
    JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);  //testre lehet szabni

    public CreateCalendar(JFrame parent) {
        super(parent);
        setTitle("Új naptár létrehozása");
        setContentPane(CreateCalendarPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //CreateCalendarPanel.add(datePicker);

        setVisible(true);
    }

    //Napi naptár
    CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
    Calendar dailyCalendar = dailyCalendarFactory.createCalendar();


    //Heti naptár
    CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
    Calendar weeklyCalendar = weeklyCalendarFactory.createCalendar();


    //Havi naptár
    CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
    Calendar monthlyCalendar = monthlyCalendarFactory.createCalendar();


    private void createUIComponents() {
        calendarTypeComboBox = new JComboBox<>(CalendarTypeEnum.values());
    }

}

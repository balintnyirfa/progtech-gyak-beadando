import javax.swing.*;
import java.awt.*;
//import org.jdatepicker.JDatePicker;
import com.toedter.calendar.JDateChooser;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import java.util.Calendar;

public class CreateCalendar extends JDialog{

    private JPanel CreateCalendarPanel;
    private JTextField calendarTitle;
    private JComboBox<CalendarTypeEnum> calendarTypeComboBox;
    private JButton createCalendar_btn;
    private JPanel startDateChooserJP;
    private JPanel endDateChooserJP;

    Calendar cld = Calendar.getInstance(); //Aktuális dátum
    JDateChooser startDateChooser = new JDateChooser(cld.getTime()); //Dátum választó mai dátummal kezdőértékként.
    JDateChooser endDateChooser = new JDateChooser(cld.getTime());

    public CreateCalendar(JFrame parent) {
        super(parent);
        setTitle("Új naptár létrehozása");
        setContentPane(CreateCalendarPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        startDateChooserJP.add(startDateChooser);
        endDateChooserJP.add(endDateChooser);


        setVisible(true);
    }

    //Napi naptár
    CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
    CalendarAbstract dailyCalendar = dailyCalendarFactory.createCalendar();


    //Heti naptár
    CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
    CalendarAbstract weeklyCalendar = weeklyCalendarFactory.createCalendar();


    //Havi naptár
    CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
    CalendarAbstract monthlyCalendar = monthlyCalendarFactory.createCalendar();


    private void createUIComponents() {
        calendarTypeComboBox = new JComboBox<>(CalendarTypeEnum.values());
    }

}

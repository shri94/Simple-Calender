package hw4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateEventDialog {
	public static void DisplayDialog(CalendarWithEvents cal)
	{
		JTextField titleF = new JTextField(20);
		JTextField dateF = new JTextField(10);
		JTextField startTimeF = new JTextField(5);
		JTextField endTimeF = new JTextField(5);
		
		titleF.setText("Unnamed Event");
		dateF.setText((cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
		
		JPanel consoleP = new JPanel();
		consoleP.add(new JLabel("Event Name"));
		consoleP.add(titleF);
		consoleP.add(new JLabel("Date"));
		consoleP.add(dateF);
		consoleP.add(new JLabel("Start Time"));
		consoleP.add(startTimeF);
		consoleP.add(new JLabel("End Time"));
		consoleP.add(endTimeF);
		
		int option = JOptionPane.showConfirmDialog(null, consoleP, "Create Event", JOptionPane.OK_CANCEL_OPTION);
		
		if(option == JOptionPane.OK_OPTION)
		{
			String optTitle = titleF.getText();
			SimpleDateFormat eDate = new SimpleDateFormat("MM/dd/yyyy");

			try {
				
				Date date = (Date) eDate.parse(dateF.getText());
				
				LocalTime startTime = parseTime(startTimeF.getText());
				
				LocalTime endTime = parseTime(endTimeF.getText());
				
				Event ev = new Event(optTitle, date, startTime, endTime);
				
				if (overlappingEvent(cal, ev))
				throw new Exception("Overlapping Event!");
				
				cal.addEvent(ev);
			} 
			catch (DateTimeParseException | ParseException e) 
			{
				JOptionPane.showMessageDialog(null, "Incorrect time format", "Input Error", JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private static LocalTime parseTime(String s) throws Exception{
		boolean am = s.endsWith("am") || s.endsWith("AM");
		boolean pm = s.endsWith("pm") || s.endsWith("PM");
		int hour = 0, min = 0;
		
		if (am && pm)
			throw new Exception("Cannot parse " + s + " as a time.");
		if (am)
		{
			s = s.replaceAll("am", "");
			s = s.replaceAll("AM", "");
		}
		if (pm)
		{
			s = s.replaceAll("pm", "");
			s = s.replaceAll("PM", "");
		}
		
		if (s.contains(":")){
			String[] tokens = s.split(":");
			hour = Integer.parseInt(tokens[0]);
			min = Integer.parseInt(tokens[1]);
		}
		else{
			hour = Integer.parseInt(s);
			min = 0;
		}
		
		if (pm && hour < 12) hour += 12;
		
		return LocalTime.of(hour, min);
	}
	
	private static boolean overlappingEvent(CalendarWithEvents cal, Event ev){
		Calendar clStart = Calendar.getInstance();
		clStart.set(ev.getYear(), ev.getMonth(), ev.getDay(), ev.getStartTime().getHour(), ev.getStartTime().getMinute());
		
		Calendar clEnd = Calendar.getInstance();
		clEnd.set(ev.getYear(), ev.getMonth(), ev.getDay(), ev.getEndTime().getHour(), ev.getEndTime().getMinute());
		
		return cal.getEventsAgenda(clStart, clEnd).size() > 0;
	}
}

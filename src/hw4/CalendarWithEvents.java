package hw4;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class CalendarWithEvents extends GregorianCalendar
{
	private ArrayList<ChangeListener> changeListeners = new ArrayList<ChangeListener>();
	private static ArrayList<ChangeListener> eventListeners = new ArrayList<ChangeListener>();
	private static ArrayList<Event> events = new ArrayList<Event>();
	
	public CalendarWithEvents()
	{	
	}
	
	public void addChangeListener(ChangeListener l)
	{
		changeListeners.add(l);
	}
	
	public void addEventListener(ChangeListener l)
	{
		eventListeners.add(l);
	}
	
	@Override
	public void set(int field, int value)
	{
		super.set(field, value);
		notifyOfChange();
	}
	
	public void addEvent(Event event)
	{
		events.add(event);
		notifyOfChange();
	}
	
	public void addEvents(ArrayList<Event> events)
	{
		for (Event e : events)
		{
			CalendarWithEvents.events.add(e);
		}
		notifyOfChange();
	}
	public static ArrayList<Event> getEvents()
	{
		return events;
	}
	
	public static void loadEvent() throws Exception
	{
		Scanner inFile = new Scanner(Paths.get("events.txt"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		while(inFile.hasNextLine()){
			String[] line = inFile.nextLine().split(";");
			String evTitle = line[0];
			Date date = (Date) dateFormat.parse(line[2]+"/"+line[3]+"/"+line[1]);
			
			
			//Event start and end time
			LocalTime sTime = LocalTime.parse(line[4]);
			LocalTime eTime = LocalTime.parse(line[5]);

			
			Event aEvent = new Event(evTitle, date, sTime, eTime);
			if(!events.contains(aEvent)) events.add(aEvent);
			
			
		}
		inFile.close();
		
	}
	
	public ArrayList<Event> getEventsAgenda(Date startDate, Date endDate)
	{
		Calendar clStart = Calendar.getInstance();
		Calendar clEnd = Calendar.getInstance();
		clStart.setTime(startDate);
		clEnd.setTime(endDate);
		
		return getEventsAgenda(clStart, clEnd);
	}
	
	
	private void notifyOfChange(){
		ChangeEvent ce = new ChangeEvent(this);
		for (ChangeListener l : changeListeners)
			l.stateChanged(ce);
	}
	
	public ArrayList<Event> getEventsAgenda(Calendar clStart, Calendar clEnd)
	{
		ArrayList<Event> thisEvents = new ArrayList<Event>();
		
		if (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH))
		{
			clEnd.add(Calendar.DAY_OF_MONTH, 1);
		}
		for (Event e : events)
		{
			Calendar startEventCal = Calendar.getInstance();
			startEventCal.set(e.getYear(), e.getMonth(), e.getDay(), e.getStartTime().getHour(), e.getStartTime().getMinute());
			Calendar endEventCal = Calendar.getInstance();
			endEventCal.set(e.getYear(), e.getMonth(), e.getDay(), e.getEndTime().getHour(), e.getEndTime().getMinute());
			
			if((clStart.before(startEventCal) && clEnd.after(startEventCal))
					|| (clStart.before(endEventCal) && clEnd.after(endEventCal))){
				thisEvents.add(e);
			}
		}
		return thisEvents;
	}
	
	public ArrayList<Event> getEventsOnDate(int day, int month, int year)
	{
		ArrayList<Event> dateEvents = new ArrayList<Event>();
		
		for (Event e : events){
			if (month == e.getMonth()
				&& year == e.getYear()
				&& day == e.getDay())
			{
				dateEvents.add(e);
			}
		}
		
		return dateEvents;
	}
	
	public ArrayList<Event> getEventsToday()
	{
		ArrayList<Event> dayEvents = new ArrayList<Event>();
		
		for (Event e : events)
		{
			if (get(Calendar.MONTH) == e.getMonth()
				&& get(Calendar.YEAR) == e.getYear()
				&& get(Calendar.DAY_OF_MONTH) == e.getDay())
			{
				dayEvents.add(e);
			}
		}
		
		return dayEvents;
	}
	

	
}

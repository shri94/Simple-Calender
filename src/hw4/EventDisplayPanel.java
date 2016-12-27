package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class EventDisplayPanel extends JPanel
{
	public enum FilterType{
		Day
	}
	
	private CalendarWithEvents cal;
	private FilterType filter = FilterType.Day;
	private int width;
	private int height;
	private JPanel eventView;
	
	public EventDisplayPanel(CalendarWithEvents c, int wd, int ht) {
		// TODO Auto-generated constructor stub
		this.cal = c;
		
		eventView = new DayEventsView(c.getEventsToday(), wd, ht);
		
		setLayout(new BorderLayout());
		add(eventView, BorderLayout.CENTER);
		
		cal.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				refreshView();
			}
		});
		this.width = wd;
		this.height = ht;
		

		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		
	}
	
	public void setFilterType(FilterType t){
		filter = t;
		refreshView();
	}
	
	private void refreshView(){
		ArrayList<Event> eventList = new ArrayList<Event>();
		removeAll();
		if (filter == FilterType.Day){
			eventList = cal.getEventsToday();
			eventView = new DayEventsView(eventList, width, height);
		}
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		add(eventView, BorderLayout.CENTER);
		revalidate();
		repaint();

	}
}

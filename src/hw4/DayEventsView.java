package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DayEventsView extends JPanel
{
	public DayEventsView(ArrayList<Event> events, int width, int height)
	{
		JTextArea textA = new JTextArea();
		textA.setEditable(true);
		display(events, textA);
		add(textA, BorderLayout. CENTER);
		this.setForeground(Color.BLUE);
		this.setForeground(Color.BLACK);
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		
	}
	
	public void display(ArrayList<Event> eve, JTextArea textArea)
	{
		
		Collections.sort(eve, new EventComparator());
		String display = "";
		
		
		for(Event e: eve)
		{
			display += (e.getMonth() + 1) + "/" + e.getDay() + "/" + e.getYear() + "\t"
					+ e.getStartTime() + ":" + e.getEndTime()  + "\t" 
					+ e.getEventTitle() + "\n";
		}
		textArea.setText(display);
	}
}

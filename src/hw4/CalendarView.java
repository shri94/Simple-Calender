package hw4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * View section
 *
 */
enum MONTHS{
	January, February, March, April, May, June, July, August, September, October, November, December;
}
enum DAYS{
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}

@SuppressWarnings("serial")
public class CalendarView extends JPanel{
	
	private DAYS[] arrayOfDays = DAYS.values();
	private MONTHS[] arrayOfMonths = MONTHS.values();
	private CalendarWithEvents cal;
	private int width;
	private int height;
	private JLabel currMonth;
	private JButton selButton; // saved selected button
	JButton currButton;
	int dateSelected;
	private ButtonColorStrat colorStrat;

	public CalendarView(CalendarWithEvents cal, int width, int height, ButtonColorStrat colorStrat)
	{
		this.colorStrat = colorStrat;
		this.cal = cal;
		this.height = height;
		this.width = width;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		
		currMonth = new JLabel(arrayOfMonths[cal.get(cal.MONTH)]+", "+cal.get(cal.YEAR));
		currMonth.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(currMonth);
		
		add(drawCalendar(cal));
		cal.addEventListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				repaintCalendar();
			}
		});
	}
	
	public CalendarView(CalendarWithEvents cal, int w, int h)
	{
		this(cal, w, h, new DefaultColorStrat());
	}
	
	public void select(JButton b, int d){
		// if there are button has been selected, set background to null
		if(selButton != null){
			colorStrat.color(selButton, dateSelected, cal, false,new Color(224, 176, 112));
		}
		
		selButton = b;
		dateSelected = d; // update selected day
		colorStrat.color(selButton, dateSelected, cal, true);
		
	}
	
	private void repaintCalendar(){
		currMonth.setText(arrayOfMonths[cal.get(Calendar.MONTH)].toString()+"; "+cal.get(Calendar.YEAR));
		repaint();
		remove(1);
		add(drawCalendar(cal));
	}
	
	public void prevDay(){
		cal.add(Calendar.DAY_OF_MONTH, -1);
		select(currButton, cal.get(Calendar.DAY_OF_MONTH));
		repaintCalendar();
	}
	
	public void nextDay(){
		cal.add(Calendar.DAY_OF_MONTH, 1);
		select(currButton, cal.get(Calendar.DAY_OF_MONTH));
		repaintCalendar();
	}
	
	private JPanel drawCalendar(Calendar c){
		int initialDay = c.get(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, 1);
		int buttonsAdded = 0;
		
		int nRows = 6;
		if(c.getActualMaximum(Calendar.DAY_OF_MONTH)==31) {
			if(c.get(Calendar.DAY_OF_WEEK) == 6 || c.get(Calendar.DAY_OF_WEEK) == 7 ){
				nRows = 7;
			}
		}else if(c.getActualMaximum(Calendar.DAY_OF_MONTH)==30){
			if(c.get(Calendar.DAY_OF_WEEK) == 7 ){
				nRows = 7;
			}
		}
		else nRows=6;
		
		JPanel calDays = new JPanel(new GridLayout(nRows,7));
		int w = (int) (width/1.2);
		int h = (int) (height/1.2);
		calDays.setPreferredSize(new Dimension(w, h));
		calDays.setMaximumSize(new Dimension(w, h));
		
		for (int j=0; j<arrayOfDays.length; j++, buttonsAdded++){
			JButton kButton = new JButton(""+arrayOfDays[j].toString().charAt(0));
			kButton.setBackground(new Color(194,188,177));
			kButton.setForeground(Color.WHITE);
			kButton.setEnabled(false);
		    kButton.setFont(new Font("Tahoma", Font.BOLD, 12));

			calDays.add(kButton);
		};
		
		int start  = c.get(Calendar.DAY_OF_WEEK)-1;
		for (int j = 0; j< start; j++, buttonsAdded++){
			JButton dummyButton = new JButton("");
			dummyButton.setEnabled(false);
			calDays.add(dummyButton);

		}
		
		for (int i=1; i<=c.getActualMaximum(Calendar.DAY_OF_MONTH); i++, buttonsAdded++){
			JButton dayButton = new JButton(""+i);
			dayButton.setBackground(new Color(188, 120, 80));
			dayButton.setForeground(Color.WHITE);
			calDays.add(dayButton);
			int day = i;
			currButton = dayButton;
			dayButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					cal.set(Calendar.DAY_OF_MONTH, day);
					currButton = dayButton;
					select(currButton,cal.get(Calendar.DAY_OF_MONTH));
				}
			});
			
			boolean selected = (day == dateSelected) || (dateSelected > c.getActualMaximum(Calendar.DAY_OF_MONTH) &&
					day == c.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			colorStrat.color(dayButton, day, cal, selected, new Color(224, 176, 112));
			
			if (selected)
			{
				selButton = dayButton;
			}
				
			
		}
		
		//Add dummy buttons for days at the end of the month
		for (; buttonsAdded < nRows * 7; buttonsAdded++){
			JButton dummyButton = new JButton("");
			dummyButton.setEnabled(false);
			calDays.add(dummyButton);
		}

		c.set(Calendar.DAY_OF_MONTH, initialDay);
		return calDays;
	}
}

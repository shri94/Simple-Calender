package hw4;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Client End
 * @author Shridhar_94
 *
 */
public class SimpleCalendar {
	
	private static final int WIDTH = 1050;
	private static final int HEIGHT = 700;
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			CalendarWithEvents.loadEvent();
			eventList = CalendarWithEvents.getEvents();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		CalendarWithEvents cal = new CalendarWithEvents();
		
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		
		frame.setLayout(new FlowLayout());
		
		JPanel lPanel = new JPanel();
		JPanel rPanel = new JPanel();
		
		//left Panel
		lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.PAGE_AXIS));
		CalNavigationPanel calPanel = new CalNavigationPanel();
		CalendarView calView = new CalendarView(cal, 500, 500);
		
		//Calendar View
		calPanel.getPrevButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				calView.prevDay();
			}
		});
		calPanel.getNextButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				calView.nextDay();
			}
		});	
		calPanel.getCreateButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				CreateEventDialog.DisplayDialog(cal);
			}
		});	
		
		lPanel.add(calView);
		lPanel.add(calPanel);
		//Right Panel
		rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.PAGE_AXIS));
	
		JButton quitButton = new JButton("Close");
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
					try
					{
						PrintWriter writer = new PrintWriter("events.txt", "UTF-8");	
						for(Event e :eventList){
							writer.write(e.toString()+"\n");;
						}
						writer.close();
					}
					
					catch(Exception e )
					{
						e.printStackTrace();
					}
					frame.dispose();
				}
		});
	
		rPanel.add(quitButton);
		EventDisplayPanel eventDisplay = new EventDisplayPanel(cal, 500, 500);
		eventDisplay.setVisible(true);
		eventDisplay.setForeground(Color.WHITE);
		eventDisplay.setBackground(Color.BLACK);
		rPanel.add(eventDisplay);
	
		
		frame.add(lPanel);
		frame.add(rPanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.pack();
	}
}
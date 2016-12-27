package hw4;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.JButton;

public class DefaultColorStrat implements ButtonColorStrat {

	@Override
	public void color(JButton button, int date, CalendarWithEvents cal, boolean selected) {
		// TODO Auto-generated method stub
		if (cal.getEventsOnDate(date, cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)).size() > 0)
		{
			button.setForeground(Color.WHITE);
		}
		else
		{
			button.setForeground(Color.BLACK);
		}
		
		button.setBackground((selected) ? Color.WHITE : null);
	}
	
	@Override
	public void color(JButton button, int date, CalendarWithEvents cal, boolean selected,Color bgColor) {
		// TODO Auto-generated method stub
		if (cal.getEventsOnDate(date, cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)).size() > 0)
		{
			button.setForeground(Color.WHITE);
		}
		else
		{
			button.setForeground(Color.BLACK);
		}
		
		button.setBackground((selected) ? Color.WHITE : bgColor);
	}

}


//addCancelButtonActionListener
	private void createCustomerViewReservationListeners()
	{
		CustomerViewReservationsPanel cvrp = view.getCustomerPanel().getCustomerViewReservationsPanel();
		cvrp.addPopulateButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				populateUserReservations();
			}
		});
		
		cvrp.addCancelButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				populateUserReservations();
			}
		});
	}
private void populateUserReservations()
	{
		CustomerViewReservationsPanel cvrp = view.getCustomerPanel().getCustomerViewReservationsPanel();
		
		TreeSet<Customer> cus = model.getCurrentCustomer();
		// Gets the available rooms by date

		
	}
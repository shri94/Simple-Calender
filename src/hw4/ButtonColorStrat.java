package hw4;

import java.awt.Color;

import javax.swing.JButton;

public interface ButtonColorStrat {
	public void color(JButton button, int date, CalendarWithEvents cal, boolean selected);
	public void color(JButton button, int date, CalendarWithEvents cal, boolean selected,Color bgColor);
}

package hw4;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CalNavigationPanel extends JPanel{
	private JButton todayBtn;
	private JButton createBtn;
	private JButton nextBtn;
	private JButton prevBtn;
	
	public CalNavigationPanel()
	{
		createBtn = new JButton("Create");
		createBtn.setBackground(new Color(152, 141, 124));
		createBtn.setForeground(Color.WHITE);
		prevBtn = new JButton("<");
		prevBtn.setBackground(new Color(152, 141, 124));
		prevBtn.setForeground(Color.WHITE);
		nextBtn = new JButton(">");
		nextBtn.setBackground(new Color(152, 141, 124));
		nextBtn.setForeground(Color.WHITE);
		
		add(prevBtn);
		add(createBtn);		
		add(nextBtn);
	}
	
	public JButton getNextButton() 
	{
		return nextBtn; 
	}
	
	public JButton getCreateButton() 
	{ 
		return createBtn; 
	}
	
	public JButton getTodayButton()
	{
		return todayBtn;
	}
	
	public JButton getPrevButton() 
	{ 
		return prevBtn; 
	}
	
}

package project3;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;



public class textBoxes extends JPanel
{
	//Declaration of variables
	private static String newDaily,newCurrent,newStarting,newAgeRetire,newRetireIncome,newDeath,newInterest,newInflation;
	
	private static Double dnewDaily = 5.0;
	private static Double dnewCurrent = 50.0;
	private static Double dnewStarting = 1500.0;
	private static Double dnewAgeRetire = 70.0;
	private static Double dnewRetireIncome = 100.0;
	private static Double dnewDeath = 80.0;
	private static Double dnewInterest = 5.0;
	private static Double dnewInflation = 2.0;
	
	//declaration of JTextFields
	private static JTextField textBox1, textBox2, textBox3,textBox4,textBox5,textBox6,textBox7,textBox8;
	
	
	public textBoxes()
	{		
		
		//setting the layout to grid
		setLayout (new GridLayout(10,2));
		
		//making label and and JTextFields 
		JLabel query1 = new JLabel("Daily Savings:");
		add(query1);
		textBox1 = new JTextField("5", 5);
		add(textBox1);
		textBox1.getDocument().addDocumentListener(new textFieldListener());
		
		JLabel query2 = new JLabel("Current Age:");
		add(query2);
		textBox2 = new JTextField("50", 5);
		add(textBox2);
		textBox2.getDocument().addDocumentListener(new textFieldListener());
		
		JLabel query3 = new JLabel("Initial Savings amount:");
		add(query3);
		textBox3 = new JTextField("1500", 5);
		add(textBox3);
		textBox3.getDocument().addDocumentListener(new textFieldListener());

		JLabel query4 = new JLabel("Age of Retirement:");
		add(query4);
		textBox4 = new JTextField("70", 5);
		add(textBox4);
		textBox4.getDocument().addDocumentListener(new textFieldListener());

		JLabel query5 = new JLabel("Annual Retirement Income:");
		add(query5);
		textBox5 = new JTextField("100", 5);
		add(textBox5);
		textBox5.getDocument().addDocumentListener(new textFieldListener());

		JLabel query6 = new JLabel("Life Expectancy:");
		add(query6);
		textBox6 = new JTextField("80", 5);
		add(textBox6);
		textBox6.getDocument().addDocumentListener(new textFieldListener());
		
		JLabel query7 = new JLabel("Interest Rate on Return of Savings:");
		add(query7);
		textBox7 = new JTextField("5", 5);
		add(textBox7);
		textBox7.getDocument().addDocumentListener(new textFieldListener());
		
		JLabel query8 = new JLabel("Inflation %:");
		add(query8);
		textBox8 = new JTextField("2", 5);
		add(textBox8);
		textBox8.getDocument().addDocumentListener(new textFieldListener());
	}
	
	
	static bars [] temp;
	
	static int barArrayLint;
	
	//Using a document listener 
	class textFieldListener implements DocumentListener 
	{
	
		//Triggers when there is an addition to the textfields
	    public void insertUpdate(DocumentEvent e) 
	    {
	    	newDaily = textBox1.getText();
	    	newCurrent = textBox2.getText();
	    	newStarting = textBox3.getText();
	    	newAgeRetire = textBox4.getText();
	    	newRetireIncome = textBox5.getText();
	    	newDeath = textBox6.getText();
	    	newInterest = textBox7.getText();
	    	newInflation = textBox8.getText();
	    	
	    	//Parses the numbers if the field is not blank
	    	if(newDaily.equals("")||newCurrent.equals("")||newStarting.equals("")||newAgeRetire.equals("")||newRetireIncome.equals("")||newDeath.equals("")||newInterest.equals("")||newInflation.equals(""))
	    	{
	    		System.out.println("Not all fields are full");
	    	}
	    	
	    	//Parses the numbers if the field includes no numbers
	    	else if(newDaily.equals("[A-Za-z]+")||newCurrent.equals("[A-Za-z]+")||newStarting.equals("[A-Za-z]+")||newAgeRetire.equals("[A-Za-z]+")||newRetireIncome.equals("[A-Za-z]+")||newDeath.equals("[A-Za-z]+")||newInterest.equals("[A-Za-z]+")||newInflation.equals("[A-Za-z]+"))
	    	{
	    		System.out.println("Cant use letters :)");
	    	}
	    	else
	    	{
	    		setDNewDaily(generalMeth.parser(newDaily));
	    		setDNewCurrent(generalMeth.parser(newCurrent));
	    		setDNewStarting(generalMeth.parser(newStarting));
	    		setDNewAgeRetire(generalMeth.parser(newAgeRetire));
	    		setDNewRetireIncome(generalMeth.parser(newRetireIncome));
	    		setDNewDeath(generalMeth.parser(newDeath));
	    		setDNewInterest(generalMeth.parser(newInterest));
	    		setDNewInflation(generalMeth.parser(newInflation));
	    	}
	    	
	    }
	    
	    
	    //copy of the previous method, but for when a character is removed
	    public void removeUpdate(DocumentEvent e) 
	    {
	    	newDaily = textBox1.getText();
	    	newCurrent = textBox2.getText();
	    	newStarting = textBox3.getText();
	    	newAgeRetire = textBox4.getText();
	    	newRetireIncome = textBox5.getText();
	    	newDeath = textBox6.getText();
	    	newInterest = textBox7.getText();
	    	newInflation = textBox8.getText();
	    	
	    	if(newDaily.equals("")||newCurrent.equals("")||newStarting.equals("")||newAgeRetire.equals("")||newRetireIncome.equals("")||newDeath.equals("")||newInterest.equals("")||newInflation.equals(""))
	    	{
	    		System.out.println("Not all fields are full");
	    	}
	    	else if(newDaily.equals("[A-Za-z]+")||newCurrent.equals("[A-Za-z]+")||newStarting.equals("[A-Za-z]+")||newAgeRetire.equals("[A-Za-z]+")||newRetireIncome.equals("[A-Za-z]+")||newDeath.equals("[A-Za-z]+")||newInterest.equals("[A-Za-z]+")||newInflation.equals("[A-Za-z]+"))
	    	{
	    		System.out.println("Cant use letters :)");
	    	}
	    	else
	    	{
	    		setDNewDaily(generalMeth.parser(newDaily));
	    		setDNewCurrent(generalMeth.parser(newCurrent));
	    		setDNewStarting(generalMeth.parser(newStarting));
	    		setDNewAgeRetire(generalMeth.parser(newAgeRetire));
	    		setDNewRetireIncome(generalMeth.parser(newRetireIncome));
	    		setDNewDeath(generalMeth.parser(newDeath));
	    		setDNewInterest(generalMeth.parser(newInterest));
	    		setDNewInflation(generalMeth.parser(newInflation));
	    		
	    	}
	    	
	    }
	    
	    //empty, but a necessary method for the document listener
	    public void changedUpdate(DocumentEvent e)
	    {
	    	
	    }
	}
	

	
	//Getters and Setters for the double variables
	public void setDNewDaily(double dnewDaily)
	{
		this.dnewDaily = dnewDaily;
	}
	
	public void setDNewCurrent(double dnewCurrent)
	{
		this.dnewCurrent = dnewCurrent;
	}
	
	public void setDNewStarting(double dnewStarting)
	{
		this.dnewStarting = dnewStarting;
	}
	
	public void setDNewAgeRetire(double dnewAgeRetire)
	{
		this.dnewAgeRetire = dnewAgeRetire;
	}
	
	public void setDNewRetireIncome(double dnewRetireIncome)
	{
		this.dnewRetireIncome = dnewRetireIncome;
	}
	
	public void setDNewDeath(double dnewDeath)
	{
		this.dnewDeath = dnewDeath;
	}
	
	public void setDNewInterest(double dnewInterest)
	{
		this.dnewInterest = dnewInterest;
	}
	
	public void setDNewInflation(double dnewInflation)
	{
		this.dnewInflation = dnewInflation;
	}
	
	
	public static double getDNewDaily()
	{
		return dnewDaily;
	}
	
	public static double getDNewCurrent()
	{
		return dnewCurrent;
	}
	
	public static double getDNewStarting()
	{
		return dnewStarting;
	}
	
	public static double getDNewAgeRetire()
	{
		return dnewAgeRetire;
	}
	
	public static double getDNewRetireIncome()
	{
		return dnewRetireIncome;
	}
	
	public static double getDNewDeath()
	{
		return dnewDeath;
	}
	
	public static double getDNewInterest()
	{
		return dnewInterest;
	}
	
	public static double getDNewInflation()
	{
		return dnewInflation;
	}
	
}
	



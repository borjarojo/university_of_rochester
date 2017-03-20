package project3;

import java.awt.Color;


public class generalMeth {
	
	
	
	//Method that parses to a double from a string
	public static double parser(String a)
	{
		double newNum;
		
			newNum = Double.parseDouble(a);
		
		return newNum;
	}
	
	
	//Method for calculating annual savings for the current age and retirement
	public static double calculatorPlus(double Daily, double Current, double Starting, double RetireAge, double RetireIncome, double Death, double Interest, double Inflation)
	{

		double startTemp = Starting;
		double interestTemp;
		interestTemp = 1+((Interest/100)/365);
		
		for(int i = 0; i <365; i++)
		{
			startTemp = (startTemp + Daily)*interestTemp;  
		}
		return startTemp;
	}
	
	
	//Method for calculating annual savings for the retirement age and death age
	public static double calculatorMinus(double Daily, double Current, double Starting, double RetireAge, double RetireIncome, double Death, double Interest, double Inflation)
	{
		double startTemp = Starting;
		double interestTemp;
		double retireTemp = 0;
		interestTemp = 1+((Interest/100)/365);
		
		for(int i = 0; i <365; i++)
		{
			retireTemp = (RetireIncome/365)*(1+(Inflation/100));
			startTemp = (startTemp - retireTemp)*interestTemp;  
		}
		return startTemp;
	}
	
	//Makes an array of doubles of saving values between current to retirement then retirement to death
	public static double [] yearValues(double Daily, double Current, double Starting, double RetireAge, double RetireIncome, double Death, double Interest, double Inflation)
	{

		//Declaration of variables
		//REAL Variables
		int retiredTemp, deathTemp, currentTemp;
		double inter [];

		int arrayCounter = 0;

		//Converting some variables
		currentTemp = (int)Current;
		retiredTemp = (int)RetireAge;
		deathTemp = (int)Death;
		int timeLine = deathTemp-currentTemp;

		inter = new double[timeLine]; 
		double initial = Starting;
		
		for(int i = currentTemp; i < retiredTemp; i++)
		{
			inter[arrayCounter] = calculatorPlus(Daily,Current,initial,RetireAge,RetireIncome,Death,Interest,Inflation);
			initial = inter[arrayCounter];
			arrayCounter++;
			
		}

		
		for(int i = retiredTemp; i < deathTemp; i++)
		{
			inter[arrayCounter] = calculatorMinus(Daily,Current,initial,RetireAge,RetireIncome,Death,Interest,Inflation);
			initial = inter[arrayCounter];
			arrayCounter++;
		}
		
		
		return inter;
	}
	
	
	//Makes an array of "bars" using the array of doubles that is the yearly savings for each year
	//Resizes according to the highest value of savings
	public static bars[] barMaker(double [] a)
	{
		//barGraph maker
		bars[] barGraph = new bars[a.length];
		int yTemp;
		int hi;
		int increaser = 10;
		double theMost;
		theMost = highest(a);
		for(int i = 0; i<a.length; i++)
		{
			if(theMost < 30000)
			{
				hi = (int)(a[i]/100);
				yTemp = (300-hi);
				if(yTemp > 300)
				{
					barGraph[i] = new bars(((13*i)+increaser),300,13, (-1*hi), Color.RED);
				}
				else
				{
					barGraph[i] = new bars(((13*i)+increaser),yTemp,13, hi, Color.GREEN);
				}
				increaser++;
			}
			else if(theMost < 60000)
			{
				hi = (int)(a[i]/200);
				yTemp = (300-hi);
				if(yTemp > 300)
				{
					barGraph[i] = new bars(((13*i)+increaser),300,13, (-1*hi), Color.RED);
				}
				else
				{
					barGraph[i] = new bars(((13*i)+increaser),yTemp,13, hi, Color.GREEN);
				}
				increaser++;
			}
			else if (theMost < 150000)
			{
				hi = (int)(a[i]/500);
				yTemp = (300-hi);
				if(yTemp > 300)
				{
					barGraph[i] = new bars(((13*i)+increaser),300,13, (-1*hi), Color.RED);
				}
				else
				{
					barGraph[i] = new bars(((13*i)+increaser),yTemp,13, hi, Color.GREEN);
				}
				increaser++;
			}
			else
			{
				hi = (int)(a[i]/3000);
				yTemp = (300-hi);
				if(yTemp > 300)
				{
					barGraph[i] = new bars(((13*i)+increaser),300,13, (-1*hi), Color.RED);
				}
				else
				{
					barGraph[i] = new bars(((13*i)+increaser),yTemp,13, hi, Color.GREEN);
				}
				increaser++;
			}
		}
		
		return barGraph;
	}
	
	public static double highest(double [] a)
	{
		double temp = 0;
		for(int i = 0; i<a.length; i++)
		{
			if(a[i] > temp)
			{
				temp = a[i];
			}
		}
		return temp;
	}
	
	
}

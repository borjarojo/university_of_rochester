package project3;

import java.awt.Color;


public class bars {

	
	int xPoint, yPoint, hi, wid;
	Color hue;

	
	//New object "bar" takes 4 ints and a color
	public bars(int xPoint, int yPoint, int hi, int wid, Color hue)
	{
		this.xPoint = xPoint;
		this.yPoint = yPoint;
		this.hi = hi;
		this.wid = wid;
		this.hue = hue;
	}
	
	
	//A bunch of getters and a setter for the color
	public int getXPoint()
	{
		return xPoint;
	}
	public int getYPoint()
	{
		return yPoint;
	}
	public int getHi()
	{
		return hi;
	}
	public int getWid()
	{
		return wid;
	}
	public Color getHue()
	{
		return hue;
	}
	
	public void setHue(Color hue)
	{
		this.hue = hue;
	}
}

package com.fARmework.modules.ScreenGestures.Data;

import java.util.LinkedList;

public class GestureData
{
	public class Point
	{
		public float X;
		public float Y;
		
		public Point(float x, float y)
		{
			X = x;
			Y = y;
		}
		
		@Override
		public String toString()
		{
			return String.format("(X = %1$s Y = %2$s)", X, Y);
		}
	}
	
	public LinkedList<Point> Points;
	
	public GestureData()
	{
		Points = new LinkedList<Point>();
	}
	
	public GestureData(LinkedList<Point> points)
	{
		Points = points;
	}
	
	public void addPoint(float x, float y)
	{
		Points.add(new Point(x, y));
	}
}

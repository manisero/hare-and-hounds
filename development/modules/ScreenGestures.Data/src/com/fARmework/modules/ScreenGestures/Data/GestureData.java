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
	}
	
	private LinkedList<Point> _points;
	
	public GestureData()
	{
		_points = new LinkedList<Point>();
	}
	
	public GestureData(LinkedList<Point> points)
	{
		_points = points;
	}
	
	public LinkedList<Point> getPoints()
	{
		return _points;
	}
	
	public void setPoints(LinkedList<Point> points)
	{
		_points = points;
	}
	
	public void addPoint(float x, float y)
	{
		_points.add(new Point(x, y));
	}
}

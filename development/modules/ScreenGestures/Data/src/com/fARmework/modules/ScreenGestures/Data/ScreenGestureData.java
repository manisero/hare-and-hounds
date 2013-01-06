package com.fARmework.modules.ScreenGestures.Data;

import java.util.LinkedList;

public class ScreenGestureData
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
	
	public LinkedList<LinkedList<Point>> Segments;
	
	public ScreenGestureData()
	{
		Segments = new LinkedList<LinkedList<Point>>();
	}
	
	public ScreenGestureData(LinkedList<LinkedList<Point>> segment)
	{
		Segments = segment;
	}
	
	public void startSegment()
	{
		Segments.add(new LinkedList<Point>());
	}
	
	public void addPoint(float x, float y)
	{
		if (Segments.isEmpty())
		{
			startSegment();
		}
		
		Segments.get(Segments.size() - 1).add(new Point(x, y));
	}
}

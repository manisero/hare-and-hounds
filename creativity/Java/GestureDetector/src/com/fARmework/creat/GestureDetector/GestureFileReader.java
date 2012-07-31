package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Data.GestureData.*;

import java.io.*;
import java.util.*;

public class GestureFileReader 
{
	private File _file;
	
	private LinkedList<GestureData> _gestures;
	
	public GestureFileReader(File file)
	{
		_file = file;
		_gestures = new LinkedList<GestureData>();
	}
	
	public void readFromFile()
	{
		try 
		{
			Scanner scanner = new Scanner(_file);
			
			scanner.useLocale(Locale.US);
			
			int totalGestures = scanner.nextInt();
			
			for(int i = 0; i < totalGestures; ++i)
			{
				int totalPoints = scanner.nextInt();
				
				GestureData gestureData = new GestureData();
				
				for(int j = 0; j < totalPoints; ++j)
				{	
					float x = scanner.nextFloat();
					float y = scanner.nextFloat();
					
					gestureData.addPoint(x, y);
				}
				
				_gestures.add(gestureData);
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println(e.getMessage());
		}	
	}
	
	public LinkedList<GestureData> getGestures()
	{
		if(_gestures.size() == 0)
		{
			readFromFile();
		}
		
		return _gestures;
	}
	
	public void print()
	{
		for(GestureData gestureData : _gestures)
		{
			System.out.println("Gesture data:");
			
			LinkedList<Point> points = gestureData.getPoints();
			
			for(Point point : points)
			{
				System.out.println("x: " + point.X + " y: " + point.Y);
			}
		}
	}
}

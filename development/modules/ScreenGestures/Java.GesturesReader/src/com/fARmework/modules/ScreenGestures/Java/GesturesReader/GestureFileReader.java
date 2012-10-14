package com.fARmework.modules.ScreenGestures.Java.GesturesReader;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.io.*;
import java.util.*;

public class GestureFileReader
{
	private File _file;
	
	private LinkedList<ScreenGestureData> _gestures;
	
	public GestureFileReader()
	{
		_gestures = new LinkedList<ScreenGestureData>();
	}
	
	public GestureFileReader(File file)
	{
		this();
		
		_file = file;
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
				
				ScreenGestureData gestureData = new ScreenGestureData();
				
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
	
	public LinkedList<ScreenGestureData> getGestures()
	{
		if(_gestures.size() == 0)
		{
			readFromFile();
		}
		
		return _gestures;
	}
	
	public void clear()
	{
		_gestures.clear();
	}
	
	public void setFile(File file)
	{
		_file = file;
	}
}
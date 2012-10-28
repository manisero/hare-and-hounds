package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers._impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataLoader;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;

public class DataLoader implements IDataLoader
{
	public LinkedList<AccelerometerData> loadData(String filename, boolean hasTime)
	{
		LinkedList<AccelerometerData> data = new LinkedList<AccelerometerData>();
		
		try 
		{
			FileInputStream fileStream = new FileInputStream(filename);
			DataInputStream dataStream = new DataInputStream(fileStream);
			InputStreamReader inputReader = new InputStreamReader(dataStream);
			
			BufferedReader reader = new BufferedReader(inputReader);
			
			String inputLine;
			
			while((inputLine = reader.readLine()) != null)
			{
				String[] splitLine = inputLine.split(";");
				
				int init = hasTime ? 1 : 0;
				
				float x = Float.valueOf(splitLine[init++].replace(',', '.'));
				float y = Float.valueOf(splitLine[init++].replace(',', '.'));
				float z = Float.valueOf(splitLine[init].replace(',', '.'));
				
				data.add(new AccelerometerData(x, y, z));
			}
			
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("No file " + filename + " found!");
			System.exit(0);
		} 
		catch (IOException e) 
		{
			System.out.println("Could not read from file " + filename);
			System.exit(0);
		}
		
		return data;
	}
}

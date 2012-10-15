package com.fARmework.Creativity.SpikeDetector;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class AccelerometerDataLoader 
{
	public List<AccelerometerData> loadData(String filename, boolean hasTime)
	{
		List<AccelerometerData> data = new LinkedList<AccelerometerData>();
		
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
				
				float[] accelerometerData = new float[3];
				
				int init = hasTime ? 1 : 0;
				
				accelerometerData[0] = Float.valueOf(splitLine[init++]);
				accelerometerData[1] = Float.valueOf(splitLine[init++]);
				accelerometerData[2] = Float.valueOf(splitLine[init]);
				
				data.add(new AccelerometerData(accelerometerData));
			}
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

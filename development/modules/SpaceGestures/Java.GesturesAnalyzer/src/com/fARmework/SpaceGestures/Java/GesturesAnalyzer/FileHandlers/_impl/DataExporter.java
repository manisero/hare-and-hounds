package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers._impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataExporter;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Java.Processing.GestureRange;
import com.fARmework.modules.SpaceGestures.Java.Utilities.INetAccelerationForceCalculator;
import com.google.inject.Inject;

public class DataExporter implements IDataExporter
{
	private INetAccelerationForceCalculator _netForceCalculator;
	
	@Inject
	public DataExporter(INetAccelerationForceCalculator netForceCalculator)
	{
		_netForceCalculator = netForceCalculator;
	}
	
	public void export(String filename, SpaceGestureData gesture, List<GestureRange> ranges)
	{
		float[] data = _netForceCalculator.getNetAccelerationForce(gesture);
		
		try
		{
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			int indent = 0;
			
			for (GestureRange range : ranges)
			{					
				for (int i = range.FirstSample; i <= range.LastSample; ++i)
				{					
					if (i != range.FirstSample)
					{
						bufferedWriter.write('\n');
						
						for (int j = 0; j < indent; ++j)
						{
							bufferedWriter.write(";");
						}
					}
					
					bufferedWriter.write("" + String.valueOf(data[i]).replace('.', ',') + ";");
				}
				
				indent++;
			}
			
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("Could not write to a file: " + filename);
			e.printStackTrace();
		}
	}
}

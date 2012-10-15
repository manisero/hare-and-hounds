package com.fARmework.Creativity.SpikeDetector;

import java.io.*;
import java.util.*;

public class AccelerometerDataExporter 
{
	public void export(String filename, float[] data, List<OscillationRange> ranges)
	{
		try
		{
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			int indent = 0;
			
			for (OscillationRange range : ranges)
			{					
				for (int i = range.OscillationBegin; i <= range.OscillationEnd; ++i)
				{					
					if (i != range.OscillationBegin)
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
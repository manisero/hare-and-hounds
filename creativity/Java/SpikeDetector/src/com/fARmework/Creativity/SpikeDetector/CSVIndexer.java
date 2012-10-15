package com.fARmework.Creativity.SpikeDetector;

import java.io.*;
import java.util.*;

public class CSVIndexer
{
	public List<String> getCSVFilenames(String workingDirectory)
	{
		List<String> csvFiles = new LinkedList<String>();
		
		File[] files = new File(workingDirectory).listFiles();
		
		for (File file : files)
		{
			if (file.isFile())
			{
				String extension = FileUtilities.getExtension(file.getName());

				if (extension.toLowerCase().equals(".csv"))
				{
					csvFiles.add(file.getName());
				}
			}
		}
		
		return csvFiles;
	}
}

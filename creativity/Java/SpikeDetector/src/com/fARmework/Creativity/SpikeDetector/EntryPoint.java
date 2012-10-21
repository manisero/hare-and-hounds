package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class EntryPoint 
{	
	private static final boolean HAS_TIME = true;
	
	public static void main(String[] args)
	{
		String workingDirectory = System.getProperty("user.dir");
		
		CSVIndexer indexer = new CSVIndexer();
		
		List<String> csvs = indexer.getCSVFilenames(workingDirectory);
		
		AccelerometerDataLoader dataLoader = new AccelerometerDataLoader();
		AccelerometerDataExporter dataExporter = new AccelerometerDataExporter();
		AccelerometerDataUtilities dataUtilities = new AccelerometerDataUtilities();
		
		for (String csv : csvs)
		{			
			List<AccelerometerData> data = dataLoader.loadData(csv, HAS_TIME);
			
			float[] product = dataUtilities.getAccelerationProduct(data);
			
			Segmenter oscillation = new Segmenter();
			
			List<SegmentRange> oscillationRanges = oscillation.getSegments(product);
			
			dataExporter.export("ranges_" + csv, product, oscillationRanges);
		}
	}
}

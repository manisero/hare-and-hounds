package com.fARmework.SpaceGestures.Java.GesturesAnalyzer;

import java.util.LinkedList;
import java.util.List;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.DataExporter;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.DataLoader;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.FileIndexer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.FileUtilities;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;
import com.fARmework.modules.SpaceGestures.Java.Direction;
import com.fARmework.modules.SpaceGestures.Java.Processing.GestureRange;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureDirectionRecognizer;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureFilter;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureSegmentator;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureDirectionRecognizer;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureFilter;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureSegmentator;
import com.fARmework.modules.SpaceGestures.Java.Utilities.INetAccelerationForceCalculator;
import com.fARmework.modules.SpaceGestures.Java.Utilities._impl.NetAccelerationForceCalculator;

public class EntryPoint
{
	private static final boolean HAS_TIME = true;
	private static final String CSV_EXTENSION = ".csv";
	
	public static void main(String[] args)
	{
		String workingDirectory = System.getProperty("user.dir");
		
		FileUtilities fileUtilities = new FileUtilities();
		FileIndexer indexer = new FileIndexer(fileUtilities);
		
		List<String> csvs = indexer.getFilenames(workingDirectory, CSV_EXTENSION);
		
		DataLoader dataLoader = new DataLoader();
		DataExporter dataExporter = new DataExporter();
		
		for (String csv : csvs)
		{			
			LinkedList<AccelerometerData> data = dataLoader.loadData(csv, HAS_TIME);
			
			SpaceGestureData gestureData = new SpaceGestureData(data);
			
			INetAccelerationForceCalculator netForceCalculator = new NetAccelerationForceCalculator();
			
			ISpaceGestureSegmentator segmentator = new SpaceGestureSegmentator(netForceCalculator);
			
			List<GestureRange> gestureRanges = segmentator.getGestureSegments(gestureData);
			
			dataExporter.export(netForceCalculator, "ranges_" + csv, gestureData, gestureRanges);
			
			ISpaceGestureFilter filter = new SpaceGestureFilter(netForceCalculator);
			
			List<GestureRange> filteredRanges = filter.getFilteredSegments(gestureData, gestureRanges);
			
			ISpaceGestureDirectionRecognizer recognizer = new SpaceGestureDirectionRecognizer();
			
			List<Direction> moves = recognizer.getMoveDirections(gestureData, filteredRanges);
			
			System.out.println("Moves for file: " + csv);
			
			for (Direction move : moves)
			{
				System.out.print(move.toString() + "; ");
			}
			
			System.out.println();
		}
	}
}

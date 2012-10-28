package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Analyzer._impl;

import java.util.LinkedList;
import java.util.List;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Analyzer.IGesturesAnalyzer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataExporter;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataLoader;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure.ISettingsProvider;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.IFileIndexer;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;
import com.fARmework.modules.SpaceGestures.Java.Direction;
import com.fARmework.modules.SpaceGestures.Java.Processing.GestureRange;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureDirectionRecognizer;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureFilter;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureSegmentator;
import com.google.inject.Inject;

public class GesturesAnalyzer implements IGesturesAnalyzer
{
	private ISettingsProvider _settingsProvider;
	private IFileIndexer _fileIndexer;
	private IDataLoader _dataLoader;
	private IDataExporter _dataExporter;
	private ISpaceGestureSegmentator _segmentator;
	private ISpaceGestureFilter _filter;
	private ISpaceGestureDirectionRecognizer _directionRecognizer;
	
	@Inject
	public GesturesAnalyzer(ISettingsProvider settingsProvider, IFileIndexer fileIndexer, IDataLoader dataLoader, IDataExporter dataExporter, ISpaceGestureSegmentator segmentator, ISpaceGestureFilter filter, ISpaceGestureDirectionRecognizer directionRecognizer)
	{
		_settingsProvider = settingsProvider;
		_fileIndexer = fileIndexer;
		_dataLoader = dataLoader;
		_dataExporter = dataExporter;
		_segmentator = segmentator;
		_filter = filter;
		_directionRecognizer = directionRecognizer;
	}
	
	@Override
	public void run()
	{
		boolean hasTimestamps = _settingsProvider.getHasTimestamps();
		String extension = _settingsProvider.getFileExtension();
		String relativePath = _settingsProvider.getRelativePath();
		
		String workingDirectory = System.getProperty("user.dir");
		String workingPath = workingDirectory + relativePath;
		
		List<String> csvs = _fileIndexer.getFilenames(workingPath, extension);
		
		for (String csv : csvs)
		{
			LinkedList<AccelerometerData> data = _dataLoader.loadData(workingPath + "/" + csv, hasTimestamps);
			
			SpaceGestureData gestureData = new SpaceGestureData(data);
			
			List<GestureRange> gestureRanges = _segmentator.getGestureSegments(gestureData);
			
			_dataExporter.export(workingPath + "/" + "ranges_" + csv, gestureData, gestureRanges);
			
			List<GestureRange> filteredRanges = _filter.getFilteredSegments(gestureData, gestureRanges);
			
			List<Direction> moves = _directionRecognizer.getMoveDirections(gestureData, filteredRanges);
			
			System.out.println("Moves for file: " + csv);
			
			for (Direction move : moves)
			{
				System.out.print(move.toString() + "; ");
			}
			
			System.out.println();
		}
	}
}

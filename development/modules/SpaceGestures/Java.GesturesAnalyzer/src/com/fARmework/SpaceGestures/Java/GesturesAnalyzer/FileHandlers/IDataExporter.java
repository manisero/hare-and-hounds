package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers;

import java.util.List;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Java.Processing.GestureRange;

public interface IDataExporter
{
	public void export(String filename, SpaceGestureData gesture, List<GestureRange> ranges);
}

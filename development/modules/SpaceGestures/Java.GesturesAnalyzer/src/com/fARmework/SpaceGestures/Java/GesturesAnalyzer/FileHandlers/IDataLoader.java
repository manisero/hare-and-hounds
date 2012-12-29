package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers;

import java.util.LinkedList;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;

public interface IDataLoader
{
	public LinkedList<AccelerometerData> loadData(String filename, boolean hasTime);
}

package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities._impl;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.IFileUtilities;

public class FileUtilities implements IFileUtilities
{
	public String getExtension(String filename)
	{
		return filename.substring(filename.lastIndexOf('.'));
	}
	
	public String getName(String filename)
	{
		return filename.substring(0, filename.lastIndexOf('.'));
	}
}

package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities;

public class FileUtilities
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

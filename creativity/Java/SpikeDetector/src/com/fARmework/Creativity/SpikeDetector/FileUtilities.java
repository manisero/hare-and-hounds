package com.fARmework.Creativity.SpikeDetector;

public class FileUtilities
{
	public static String getExtension(String filename)
	{
		return filename.substring(filename.lastIndexOf('.'));
	}
	
	public static String getName(String filename)
	{
		return filename.substring(0, filename.lastIndexOf('.'));
	}
}

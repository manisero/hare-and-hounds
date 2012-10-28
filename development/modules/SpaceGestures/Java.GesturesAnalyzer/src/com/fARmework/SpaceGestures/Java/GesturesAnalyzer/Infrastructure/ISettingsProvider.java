package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure;

public interface ISettingsProvider
{
	public boolean getHasTimestamps();
	public String getFileExtension();
	public String getRelativePath();
}

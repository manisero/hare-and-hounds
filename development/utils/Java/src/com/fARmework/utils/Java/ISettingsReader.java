package com.fARmework.utils.Java;

public interface ISettingsReader 
{
	void loadSettings(String settingsFileName);
	
	String get(String settingKey);
}

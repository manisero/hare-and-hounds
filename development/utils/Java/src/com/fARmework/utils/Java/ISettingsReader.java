package com.fARmework.utils.Java;

public interface ISettingsReader 
{
	void setSettingsFileName(String settingsFileName);
	
	String get(String settingKey);
}

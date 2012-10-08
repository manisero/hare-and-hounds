package com.fARmework.utils.Java;

import java.util.*;

public interface ISettingsReader 
{
	void loadSettings(String settingsFileName);
	
	String get(String settingKey);
	
	List<String> getAggregate(String aggregateSettingKey);
}

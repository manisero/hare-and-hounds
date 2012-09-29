package com.fARmework.utils.Java._impl;

import com.fARmework.utils.Java.*;

import org.apache.commons.configuration.*;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

public class SettingsReader implements ISettingsReader 
{
	private String _settingsFileName;
	
	@Override
	public void setSettingsFileName(String settingsFileName)
	{
		_settingsFileName = settingsFileName;
	}
	
	@Override
	public String get(String settingKey) 
	{
		XMLConfiguration configuration = new XMLConfiguration();
		configuration.setFileName(_settingsFileName);
		configuration.setValidating(false);
		configuration.setExpressionEngine(new XPathExpressionEngine());
		
		try
		{			
			configuration.load();
		} 
		catch(ConfigurationException exception) 
		{
			exception.printStackTrace();
		}
				
		return (String) configuration.getProperty("setting[@key='" + settingKey + "']");
	}
}

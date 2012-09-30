package com.fARmework.utils.Java._impl;

import com.fARmework.utils.Java.*;

import org.apache.commons.configuration.*;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

public class SettingsReader implements ISettingsReader 
{
	private static final String SETTING_TEMPLATE = "setting[@key='%s']";
	
	private XMLConfiguration _configuration;
	
	public SettingsReader()
	{
		_configuration = new XMLConfiguration();
		_configuration.setValidating(false);
		_configuration.setExpressionEngine(new XPathExpressionEngine());
	}
	
	@Override
	public void loadSettings(String settingsFileName)
	{
		_configuration.setFileName(settingsFileName);
		
		try
		{
			_configuration.load();
		}
		catch(ConfigurationException exception)
		{
			exception.printStackTrace();
		}
	}
	
	@Override
	public String get(String settingKey) 
	{
		try
		{
			_configuration.refresh();
		}
		catch(ConfigurationException exception)
		{
			exception.printStackTrace();
		}
		
		return (String)_configuration.getProperty(String.format(SETTING_TEMPLATE, settingKey));
	}
}

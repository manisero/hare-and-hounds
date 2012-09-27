package com.fARmework.utils.Java._impl;

import com.fARmework.utils.Java.*;

import org.apache.commons.configuration.*;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

public class DefaultSettingsReader implements ISettingsReader 
{
	private static final String SETTINGS_FILE = "settings.xml";

	@Override
	public String getSettingsFileName() 
	{
		return SETTINGS_FILE;
	}	
	
	@Override
	public String getProperty(String propertyID) 
	{
		XMLConfiguration configuration = new XMLConfiguration();
		configuration.setFileName(SETTINGS_FILE);
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
				
		return (String) configuration.getProperty("setting[@key='" + propertyID + "']");
	}
}

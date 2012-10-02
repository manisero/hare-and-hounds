package com.fARmework.HareAndHounds.HareBot.Infrastructure._impl;

import com.fARmework.HareAndHounds.HareBot.Infrastructure.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Java.*;
import com.google.inject.*;

import java.util.*;

public class SettingsProvider implements ISettingsProvider
{
	private static final String SETTINGS_FILE_NAME = "settings.xml";
	
	private ISettingsReader _settingsReader;
	
	@Inject
	public SettingsProvider(ISettingsReader settingsReader)
	{
		_settingsReader = settingsReader;
		_settingsReader.loadSettings(SETTINGS_FILE_NAME);
	}

	@Override
	public String getServerAddress()
	{
		return _settingsReader.get("ServerAddress");
	}
	
	@Override
	public int getPort()
	{
		return getInt("Port");
	}	
	
	@Override
	public String getUserName()
	{
		return _settingsReader.get("UserName");
	}	
	
	@Override
	public int getSendPositionInterval()
	{
		return getInt("SendPositionInterval");
	}	
	
	@Override
	public PositionData getInitialPosition()
	{
		return parsePositionData(_settingsReader.get("InitialPosition"));
	}
	
	@Override
	public List<PositionData> getPositions()
	{
		List<String> stringPositions = _settingsReader.getAggregate("Position");
		
		List<PositionData> positions = new ArrayList<PositionData>(); 
		
		for (String stringPosition : stringPositions)
		{
			positions.add(parsePositionData(stringPosition));
		}
		
		return positions;
	}
	
	private int getInt(String key)
	{
		return Integer.valueOf(_settingsReader.get(key));		
	}
	
	private PositionData parsePositionData(String positionData)
	{
		String[] stringPositions = positionData.split(" ");
		
		if (stringPositions.length != 2)
		{
			return null;
		}
		
		return new PositionData(Double.valueOf(stringPositions[0]), Double.valueOf(stringPositions[1]));
	}
}

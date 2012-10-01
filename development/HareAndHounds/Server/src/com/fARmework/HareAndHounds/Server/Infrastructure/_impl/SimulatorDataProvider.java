package com.fARmework.HareAndHounds.Server.Infrastructure._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Java.*;
import com.google.inject.*;

public class SimulatorDataProvider implements ISimulatorDataProvider
{
	private static final String SIMULATOR_DATA_FILE_NAME = "simulator.xml";
	
	private final ISettingsReader _settingsReader;
	
	@Inject
	public SimulatorDataProvider(ISettingsReader settingsReader)
	{
		_settingsReader = settingsReader;
		_settingsReader.loadSettings(SIMULATOR_DATA_FILE_NAME);
	}

	@Override
	public int getHareID()
	{
		return getInt("HareID");
	}

	@Override
	public String getHareHostname()
	{
		return _settingsReader.get("HareHostname");
	}

	@Override
	public PositionData getInitialHarePosition()
	{
		return getPositionData("InitialHareLatitude", "InitialHareLongitude");
	}
	
	private int getInt(String key)
	{
		return Integer.valueOf(_settingsReader.get(key));		
	}
	
	private PositionData getPositionData(String latitudeKey, String longitudeKey)
	{
		return new PositionData(Double.valueOf(_settingsReader.get(latitudeKey)), Double.valueOf(_settingsReader.get(longitudeKey)));
	}
}

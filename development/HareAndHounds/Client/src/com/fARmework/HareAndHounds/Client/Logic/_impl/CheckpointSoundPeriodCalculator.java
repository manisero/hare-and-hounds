package com.fARmework.HareAndHounds.Client.Logic._impl;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Logic.*;
import com.google.inject.*;

public class CheckpointSoundPeriodCalculator implements ICheckpointSoundPeriodCalculator
{
	private final ISettingsProvider _settingsProvider;
	
	@Inject
	public CheckpointSoundPeriodCalculator(ISettingsProvider settingsProvider)
	{
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public int calculatePeriod(double accuracy)
	{
		int maxPeriod = _settingsProvider.getCheckpointSoundMaxPeriod();
		int periodRange = maxPeriod - _settingsProvider.getCheckpointSoundMinPeriod();
		
		return maxPeriod - (int)(accuracy * periodRange);
	}
}

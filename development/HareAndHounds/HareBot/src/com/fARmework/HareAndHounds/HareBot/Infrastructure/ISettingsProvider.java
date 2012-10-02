package com.fARmework.HareAndHounds.HareBot.Infrastructure;

import com.fARmework.modules.PositionTracking.Data.*;

import java.util.*;

public interface ISettingsProvider extends com.fARmework.core.client.Infrastructure.ISettingsProvider
{
	public String getUserName();
	
	public PositionData getInitialPosition();
	public List<PositionData> getPositions();
}

package com.fARmework.HareAndHounds.HareBot.Infrastructure;

import com.fARmework.modules.PositionTracking.Data.*;

import java.util.*;

public interface ISettingsProvider
{
	public String getServerAddress();
	public int getPort();
	public String getUserName();
	
	public PositionData getInitialPosition();
	public List<PositionData> getPositions();
}

package com.fARmework.HareAndHounds.Server.Infrastructure;

import com.fARmework.modules.PositionTracking.Data.*;

public interface ISimulatorDataProvider
{
	public int getHareID();
	public String getHareHostname();
	
	public PositionData getInitialHarePosition();
}

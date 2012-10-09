package com.fARmework.HareAndHounds.Data;

import com.fARmework.modules.PositionTracking.Data.*;

public class NewGameRequest 
{
	public String HostName;
	public PositionData Position;
	
	public NewGameRequest(String hostName, PositionData position)
	{
		HostName = hostName;
		Position = position;
	}
}

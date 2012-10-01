package com.fARmework.HareAndHounds.Server.Logic;

import com.fARmework.modules.PositionTracking.Data.*;

public class GameListItem
{
	public int HostID;
	public String HostName;
	public PositionData HostPosition;
	public boolean IsAvailable = true;
	
	public GameListItem(int hostID, String hostName, PositionData hostPosition)
	{
		HostID = hostID;
		HostName = hostName;
		HostPosition = hostPosition;
	}
}

package com.fARmework.HareAndHounds.Server.Logic;

import java.util.LinkedList;

import com.fARmework.modules.PositionTracking.Data.PositionData;

public class Game
{
	public int HostID;
	public String HostName;
	
	public int GuestID;
	
	public LinkedList<PositionData> HostPositions = new LinkedList<PositionData>();
}
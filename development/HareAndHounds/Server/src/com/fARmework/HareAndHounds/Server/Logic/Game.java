package com.fARmework.HareAndHounds.Server.Logic;

import java.util.LinkedList;

import com.fARmework.modules.PositionTracking.Data.PositionData;

public class Game
{
	public int HareID;
	public int HoundsID;
	
	public LinkedList<PositionData> HarePositions = new LinkedList<PositionData>();
	
	public Game(int hareID, int houndsID)
	{
		HareID = hareID;
		HoundsID = houndsID;
	}
}
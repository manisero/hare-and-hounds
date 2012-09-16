package com.fARmework.HareAndHounds.Server.Logic;

import java.util.LinkedList;

import com.fARmework.modules.PositionTracking.Data.PositionData;

public class Game
{
	public int HareID;
	public String HareName;
	
	public int HoundsID;
	public String HoundsName;
	
	public LinkedList<PositionData> HarePositions = new LinkedList<PositionData>();
	
	public Game(int hareID, String hareName, int houndsID, String houndsName)
	{
		HareID = hareID;
		HareName = hareName;
		HoundsID = houndsID;
		HoundsName  = houndsName;
	}
}
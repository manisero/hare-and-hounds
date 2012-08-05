package com.fARmework.RockPaperScissors.Data;

public class GameJoinData
{
	public int HostID;
	public String GuestUserName;
	
	public GameJoinData(int hostID, String guestUserName)
	{
		HostID = hostID;
		GuestUserName = guestUserName;
	}
}

package com.fARmework.RockPaperScissors.Data;

public class GameJoinRequest
{
	public int HostID;
	public int GuestID;
	public String GuestUserName;
	
	public GameJoinRequest(int hostID, int guestID, String guestUserName)
	{
		HostID = hostID;
		GuestID = guestID;
		GuestUserName = guestUserName;
	}
}

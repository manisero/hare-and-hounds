package com.fARmework.HareAndHounds.Data;

public class JoinGameRequest
{
	public int HostID;
	public String GuestName;
	public int GuestID;
	
	public JoinGameRequest(int hostID, String guestName)
	{
		HostID = hostID;
		GuestName = guestName;
	}
}

package com.fARmework.RockPaperScissors.Data;

public class GameJoinResponse
{
	public int GuestID;
	public boolean Accepted;
	
	public GameJoinResponse(int guestID, boolean accepted)
	{
		GuestID = guestID;
		Accepted = accepted;
	}
}

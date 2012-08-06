package com.fARmework.RockPaperScissors.Data;

public class GameJoinResponse
{
	public enum GameJoinResponseType
	{
		Accept,
		Deny
	}
	
	public int HostID;
	public int GuestID;
	public GameJoinResponseType Response;
	
	public GameJoinResponse(int hostID, int guestID, GameJoinResponseType response)
	{
		HostID = hostID;
		GuestID = guestID;
		Response = response;
	}
}

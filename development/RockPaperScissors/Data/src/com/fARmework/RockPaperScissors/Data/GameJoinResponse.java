package com.fARmework.RockPaperScissors.Data;

public class GameJoinResponse
{
	public enum GameJoinResponseType
	{
		Accept,
		Deny,
		NotAvailable
	}
	
	public int GuestID;
	public GameJoinResponseType Response;
	
	public GameJoinResponse(int guestID, GameJoinResponseType response)
	{
		GuestID = guestID;
		Response = response;
	}
}

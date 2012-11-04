package com.fARmework.HareAndHounds.Data;

public class JoinGameResponse
{
	public enum JoinGameResponseType
	{
		Accept,
		Reject,
		Unavailable
	}
	
	public JoinGameResponseType Response;
	public int GuestID;
	
	public JoinGameResponse(JoinGameResponseType response)
	{
		Response = response;
	}
	
	public JoinGameResponse(JoinGameResponseType response, int guestID)
	{
		Response = response;
		GuestID = guestID;
	}
}

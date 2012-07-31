package com.fARmework.RockPaperScissors.Data;

public class JoinGameRequest
{
	private int _hostID;
	
	public JoinGameRequest(int hostID)
	{
		_hostID = hostID;
	}
	
	public int getHostID()
	{
		return _hostID;
	}
}

package com.fARmework.RockPaperScissors.Data;

import java.util.LinkedList;

public class GameListResponse
{
	private LinkedList<Integer> _hostIDs;
	
	public GameListResponse()
	{
	}
	
	public GameListResponse(LinkedList<Integer> hostIDs)
	{
		_hostIDs = hostIDs;
	}
	
	public LinkedList<Integer> getHostIDs()
	{
		return _hostIDs;
	}
	
	public void setHostIDs(LinkedList<Integer> hostIDs)
	{
		_hostIDs = hostIDs;
	}
}

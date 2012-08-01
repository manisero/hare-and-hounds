package com.fARmework.RockPaperScissors.Data;

import java.util.LinkedList;

public class GameListResponse
{
	public LinkedList<Integer> HostIDs;
	
	public GameListResponse(LinkedList<Integer> hostIDs)
	{
		HostIDs = hostIDs;
	}
}

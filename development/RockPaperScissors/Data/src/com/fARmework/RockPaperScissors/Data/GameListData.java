package com.fARmework.RockPaperScissors.Data;

import java.util.LinkedList;

public class GameListData
{
	public class GameInfo
	{
		public int HostID;
		public String HostUserName;
		
		public GameInfo(int hostID, String hostUserName)
		{
			HostID = hostID;
			HostUserName = hostUserName;
		}
	}
	
	public LinkedList<GameInfo> Games = new LinkedList<GameInfo>();
	
	public void addGame(int hostID, String hostUserName)
	{
		Games.add(new GameInfo(hostID, hostUserName));
	}
}

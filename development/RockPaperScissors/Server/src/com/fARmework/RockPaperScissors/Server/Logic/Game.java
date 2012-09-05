package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;

public class Game
{
	public int HostID;
	public String HostUserName;
	public GestureType HostGesture;
	public int HostScore;
	public Boolean HostWantsNextGame;
	
	public int GuestID;
	public String GuestUserName;
	public GestureType GuestGesture;
	public int GuestScore;
	public Boolean GuestWantsNextGame;
	
	public boolean HasStarted;
	public boolean HasEnded;
	
	public Game()
	{
	}
}

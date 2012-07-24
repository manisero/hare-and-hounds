package com.fARmework.RockPaperScissors.Server.Logic;

public interface IConnectionHandler
{
	void connect();
	void send(String message);
}

package com.fARmework.RockPaperScissors.Server.Logic;

public interface IDataHandler<T>
{
	void handle(int clientID, T data);
}

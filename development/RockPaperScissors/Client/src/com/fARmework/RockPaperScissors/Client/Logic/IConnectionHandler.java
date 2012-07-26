package com.fARmework.RockPaperScissors.Client.Logic;

public interface IConnectionHandler extends com.fARmework.core.client.Connection.IConnectionHandler
{
	public interface IMessageListener
	{
		void onMessage(String message);
	}
	
	void setMessageListener(IMessageListener messageListener);
}

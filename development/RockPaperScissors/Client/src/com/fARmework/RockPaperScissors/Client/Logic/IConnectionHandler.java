package com.fARmework.RockPaperScissors.Client.Logic;

public interface IConnectionHandler extends com.fARmework.core.client.Connection.IConnectionHandler
{
	// TODO: replace "Message" with something
	public interface IMessageListener
	{
		void onMessage(String message);
	}
	
	void setMessageListener(IMessageListener messageListener);
}

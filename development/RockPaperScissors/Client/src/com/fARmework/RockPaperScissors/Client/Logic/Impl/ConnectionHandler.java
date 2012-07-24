package com.fARmework.RockPaperScissors.Client.Logic.Impl;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;

public class ConnectionHandler implements IConnectionHandler
{
	private IMessageListener _messageListener;
	
	@Override
	public void setMessageListener(IMessageListener messageListener)
	{
		_messageListener = messageListener;
	}
	
	@Override
	public void onConnectionSuccess()
	{
		_messageListener.onMessage(ResourcesProvider.get(R.string.connection_success));
	}

	@Override
	public void onConnectionFault()
	{
		_messageListener.onMessage(ResourcesProvider.get(R.string.connection_fault));
	}

	@Override
	public void onDataReceived(String dataType, Object data)
	{
		_messageListener.onMessage(dataType + ": " + data.toString());
	}

	@Override
	public void onException(Throwable exception)
	{
		_messageListener.onMessage(ResourcesProvider.get(R.string.connection_error));
	}
}

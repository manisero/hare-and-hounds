package com.fARmework.RockPaperScissors.Client.Logic.Impl;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.google.inject.Inject;

public class ConnectionHandler implements IConnectionHandler
{
	private IDataService _dataService;
	
	private IMessageListener _messageListener;
	
	@Inject
	public ConnectionHandler(IDataService dataService)
	{
		_dataService = dataService;
	}
	
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
	public void onMessage(Message message)
	{
		_messageListener.onMessage(message.getType() + ": " + _dataService.fromMessage(message).toString());
	}

	@Override
	public void onException(Throwable exception)
	{
		_messageListener.onMessage(ResourcesProvider.get(R.string.connection_error));
	}
}

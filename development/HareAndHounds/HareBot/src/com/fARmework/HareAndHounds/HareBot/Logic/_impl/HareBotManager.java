package com.fARmework.HareAndHounds.HareBot.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.*;
import com.fARmework.HareAndHounds.HareBot.Infrastructure.*;
import com.fARmework.HareAndHounds.HareBot.Logic.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.google.inject.*;

import java.util.*;

public class HareBotManager implements IHareBotManager
{
	private IConnectionManager _connectionManager;
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public HareBotManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void runBot()
	{
		_connectionManager.connect();
		
		_connectionManager.registerDataHandler(NewGameResponse.class, new IDataHandler<NewGameResponse>()
		{
			@Override
			public void handle(NewGameResponse data)
			{
				System.out.println("New game created");
			}	
		});
		
		_connectionManager.registerDataHandler(JoinGameRequest.class, new IDataHandler<JoinGameRequest>()
		{
			@Override
			public void handle(JoinGameRequest data)
			{
				_connectionManager.send(new JoinGameResponse(JoinGameResponseType.Accept, data.GuestID));
				
				startThread();
			}
		});
		
		_connectionManager.send(new NewGameRequest(_settingsProvider.getUserName(), _settingsProvider.getInitialPosition()));		
	}
	
	private void startThread()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				List<PositionData> positions = _settingsProvider.getPositions();
				LinkedList<PositionData> linkedPositions = new LinkedList<PositionData>(positions);
				
				while (!linkedPositions.isEmpty())
				{
					try
					{
						Thread.sleep(_settingsProvider.getSendPositionInterval());
					}
					catch (InterruptedException exception)
					{
					}
					
					PositionData data = linkedPositions.getFirst();
					
					_connectionManager.send(data);
					
					linkedPositions.remove(data);
				}
			}
		}).start();
	}
}

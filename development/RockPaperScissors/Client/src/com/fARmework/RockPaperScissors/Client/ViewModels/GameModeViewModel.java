package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Data.ConnectionFaultData;
import com.fARmework.RockPaperScissors.Client.Data.ConnectionSuccessData;
import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Client.Logic.IDataHandler;
import com.fARmework.RockPaperScissors.Data.CreateGameRequest;
import com.fARmework.RockPaperScissors.Data.CreateGameResponse;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.google.inject.Inject;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class GameModeViewModel
{
	private IConnectionManager _connectionManager;
	
	public StringObservable status = new StringObservable();
	
	public Command create = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set(ResourcesProvider.get(R.string.connection_connecting));
			_isHost = true;
			_connectionManager.connect();
		}
	};
	
	public Command join = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set(ResourcesProvider.get(R.string.connection_connecting));
			_isHost = false;
			_connectionManager.connect();
		}
	};
	
	private boolean _isHost = false;
	
	@Inject
	public GameModeViewModel(IConnectionManager connectionManager, IConnectionHandler connectionHandler)
	{
		_connectionManager = connectionManager;
		
		connectionHandler.registerHandler(ConnectionSuccessData.class, new IDataHandler<ConnectionSuccessData>()
		{
			@Override
			public void handle(ConnectionSuccessData data)
			{
				status.set(ResourcesProvider.get(R.string.connection_success));
				
				if (_isHost)
				{
					_connectionManager.send(new CreateGameRequest());
				}
				else
				{
					_connectionManager.send(new GameListRequest());
				}
			}
		});
		
		connectionHandler.registerHandler(ConnectionFaultData.class, new IDataHandler<ConnectionFaultData>()
		{
			@Override
			public void handle(ConnectionFaultData data)
			{
				status.set(ResourcesProvider.get(R.string.connection_fault));
			}
		});
		
		connectionHandler.registerHandler(CreateGameResponse.class, new IDataHandler<CreateGameResponse>()
		{
			@Override
			public void handle(CreateGameResponse data)
			{
				status.set("game created");
			}
		});
		
		connectionHandler.registerHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
		{
			@Override
			public void handle(GameListResponse data)
			{
				String games = "";
				
				for (Integer hostID : data.getHostIDs())
				{
					games += hostID.toString() + ", ";
				}
				
				status.set("games: " + games);
			}
		});
	}
}

package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.LinkedList;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.*;

import android.os.Bundle;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.RockPaperScissors.Data.GameListData.GameInfo;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.Inject;

public class GameListViewModel extends ViewModel
{
	public class Game
	{
		private int _hostID;
		public StringObservable hostUserName = new StringObservable();
		
		public Command JoinGame = new Command()
		{
			@Override
			public void Invoke(View arg0, Object... arg1)
			{
				joinGame(_hostID, hostUserName.get());
			}
		};
		
		public Game(GameInfo game)
		{
			_hostID = game.HostID;
			hostUserName.set(game.HostUserName);
		}
	}
	
	private final ISettingsProvider _settingsProvider;
	
	public ArrayListObservable<Game> games = new ArrayListObservable<Game>(Game.class);
	public StringObservable status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(false);
	
	public Command GetGames = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(new GameListRequest());
		}
	};
	
	@Inject
	public GameListViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void onEntering()
	{
		ConnectionManager.registerDataHandler(GameListData.class, new IDataHandler<GameListData>()
		{
			@Override
			public void handle(GameListData data)
			{
				LinkedList<Game> gameList = new LinkedList<Game>();
				
				for (GameInfo game : data.Games)
				{
					gameList.add(new Game(game));
				}
				
				games.setArray(gameList.toArray(new Game[0]));
			}
		});
		
		ConnectionManager.send(new GameListRequest());
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(GameListData.class);
		ConnectionManager.unregisterDataHandlers(GameJoinResponse.class);
		ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
	}
	
	private void joinGame(int hostID, final String hostName)
	{
		status.set(String.format(ResourcesProvider.getString(R.string.gameList_joining), hostName));
		IsWaiting.set(true);
		
		ConnectionManager.registerDataHandler(GameJoinResponse.class, new IDataHandler<GameJoinResponse>()
		{
			@Override
			public void handle(GameJoinResponse data)
			{
				IsWaiting.set(false);
				
				if (data.Response == GameJoinResponseType.Deny)
				{
					ContextManager.showNotification(String.format(ResourcesProvider.getString(R.string.gameList_joinRefused), hostName));
				}
				else if (data.Response == GameJoinResponseType.NotAvailable)
				{
					ContextManager.showNotification(String.format(ResourcesProvider.getString(R.string.gameList_notAvailable), hostName));
				}
			}
		});
		
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
				
				Bundle bundle = new Bundle();
				bundle.putString(GameViewModel.OPPONENT_NAME_KEY, hostName);
				ContextManager.navigateTo(GameViewModel.class, bundle);
			}
		});
		
		ConnectionManager.send(new GameJoinRequest(hostID, _settingsProvider.getUserName()));
	}
}

package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.LinkedList;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import android.os.Bundle;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse;
import com.fARmework.RockPaperScissors.Data.GameListData.GameInfo;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListData;
import com.fARmework.RockPaperScissors.Data.GameJoinData;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class GameListViewModel extends ViewModel
{
	public class Game
	{
		private int _hostID;
		public StringObservable hostUserName = new StringObservable();
		
		public Command joinGame = new Command()
		{
			@Override
			public void Invoke(View arg0, Object... arg1)
			{
				ConnectionManager.registerDataHandler(GameJoinResponse.class, new IDataHandler<GameJoinResponse>()
				{
					@Override
					public void handle(GameJoinResponse data)
					{
						isWaiting.set(false);
						
						if (data.Accepted)
						{
							Bundle bundle = new Bundle();
							bundle.putString(GameViewModel.OPPONENT_NAME_KEY, hostUserName.get());
							ContextManager.navigateTo(GameViewModel.class, bundle);
						}
						else
						{
							ContextManager.showShortNotification(String.format(ResourcesProvider.getString(R.string.gameList_joinRefused), hostUserName.get()));
						}
					}
				});
				
				status.set(String.format(ResourcesProvider.getString(R.string.gameList_joining), hostUserName.get()));
				isWaiting.set(true);
				ConnectionManager.send(new GameJoinData(_hostID, _settingsProvider.getUserName()));
			}
		};
		
		public Game(GameInfo game)
		{
			_hostID = game.HostID;
			hostUserName.set(game.HostUserName);
		}
	}
	
	public ArrayListObservable<Game> games = new ArrayListObservable<Game>(Game.class);
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	
	public Command getGames = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(new GameListRequest());
		}
	};
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public GameListViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
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
}

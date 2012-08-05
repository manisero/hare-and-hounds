package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.LinkedList;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.StringObservable;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Data.GameListData.GameInfo;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListData;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
import com.fARmework.RockPaperScissors.Data.GameJoinRequest;
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
				ConnectionManager.send(new GameJoinRequest(_hostID));
			}
		};
		
		public Game(GameInfo game)
		{
			_hostID = game.HostID;
			hostUserName.set(game.HostUserName);
		}
	}
	
	public ArrayListObservable<Game> games = new ArrayListObservable<Game>(Game.class);
	
	public Command getGames = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(new GameListRequest());
		}
	};
	
	@Inject
	public GameListViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
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
		
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				NavigationManager.navigateTo(GameViewModel.class);
			}
		});
		
		ConnectionManager.send(new GameListRequest());
	}
}

package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.LinkedList;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.IntegerObservable;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
import com.fARmework.RockPaperScissors.Data.JoinGameRequest;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class GameListViewModel extends ViewModel
{
	public class Game
	{
		public IntegerObservable hostID = new IntegerObservable();
		
		public Command joinGame = new Command()
		{
			@Override
			public void Invoke(View arg0, Object... arg1)
			{
				ConnectionManager.send(new JoinGameRequest(hostID.get()));
			}
		};
		
		public Game(Integer hostID)
		{
			this.hostID.set(hostID);
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
		
		ConnectionManager.registerDataHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
		{
			@Override
			public void handle(GameListResponse data)
			{
				LinkedList<Game> gameList = new LinkedList<Game>();
				
				for (Integer hostID : data.getHostIDs())
				{
					gameList.add(new Game(hostID));
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
	}
}

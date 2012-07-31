package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.LinkedList;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.StringObservable;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class GameListViewModel extends ViewModel
{
	public class Game
	{
		public StringObservable hostID = new StringObservable();
		
		public Game(String hostID)
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
	public GameListViewModel(IConnectionManager connectionManager, IActivitiesManager activitiesManager)
	{
		super(connectionManager, activitiesManager);
		
		ConnectionManager.registerDataHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
		{
			@Override
			public void handle(GameListResponse data)
			{
				LinkedList<Game> gameList = new LinkedList<Game>();
				
				for (Integer hostID : data.getHostIDs())
				{
					gameList.add(new Game(hostID.toString()));
				}
				
				games.setArray(gameList.toArray(new Game[0]));
			}
		});
	}
}

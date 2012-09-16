package com.fARmework.HareAndHounds.Client.ViewModels;

import java.util.*;

import gueei.binding.*;
import gueei.binding.collections.*;
import gueei.binding.observables.*;

import android.view.*;

import com.fARmework.HareAndHounds.Client.R;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.JoinGameResponseType;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class GameListViewModel extends ViewModel
{
	public class GameListItem
	{
		private int _hostID;
		public StringObservable hostName = new StringObservable();
		
		public Command join = new Command()
		{
			@Override
			public void Invoke(View arg0, Object... arg1)
			{
				joinGame(_hostID, hostName.get());
			}
		};
		
		public GameListItem(int hostID, String hostName)
		{
			_hostID = hostID;
			this.hostName.set(hostName);
		}
	}
	
	public ArrayListObservable<GameListItem> games = new ArrayListObservable<GameListItem>(GameListItem.class);
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	
	public Command refresh = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			getGames();
		}
	};
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public GameListViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		ConnectionManager.registerDataHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
		{
			@Override
			public void handle(GameListResponse data)
			{
				LinkedList<GameListItem> gameList = new LinkedList<GameListItem>();
				
				for (int hostID : data.Games.keySet())
				{
					gameList.add(new GameListItem(hostID, data.Games.get(hostID)));
				}
				
				games.setArray(gameList.toArray(new GameListItem[0]));
			}
		});
		
		getGames();
	}
	
	private void getGames()
	{
		ConnectionManager.send(new GameListRequest(new PositionData(0, 0)));
	}
	
	private void joinGame(int hostID, final String hostName)
	{
		status.set(String.format(ResourcesProvider.getString(R.string.gameList_joining), hostName));
		isWaiting.set(true);
		
		ConnectionManager.registerDataHandler(JoinGameResponse.class, new IDataHandler<JoinGameResponse>()
		{
			@Override
			public void handle(JoinGameResponse data)
			{
				isWaiting.set(false);
				
				if (data.Response == JoinGameResponseType.Reject)
				{
					ContextManager.showShortNotification(String.format(ResourcesProvider.getString(R.string.gameList_joinRejected), hostName));
				}
				else if (data.Response == JoinGameResponseType.Unavailable)
				{
					ContextManager.showShortNotification(String.format(ResourcesProvider.getString(R.string.gameList_unavailable), hostName));
				}
			}
		});
		
		/*
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
				
				Bundle bundle = new Bundle();
				bundle.putString(GameViewModel.OPPONENT_NAME_KEY, hostUserName.get());
				ContextManager.navigateTo(GameViewModel.class, bundle);
			}
		});
		*/
		
		ConnectionManager.send(new JoinGameRequest(hostID, _settingsProvider.getUserName()));
	}
}

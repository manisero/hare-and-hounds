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
import com.fARmework.modules.PositionTracking.Android.*;
import com.fARmework.modules.PositionTracking.Android.IPositionService.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class GameListViewModel extends ViewModel
{
	public class GameListItem
	{
		private int _hostID;
		public StringObservable HostName = new StringObservable();
		
		public Command Join = new Command()
		{
			@Override
			public void Invoke(View arg0, Object... arg1)
			{
				joinGame(_hostID, HostName.get());
			}
		};
		
		public GameListItem(int hostID, String hostName)
		{
			_hostID = hostID;
			HostName.set(hostName);
		}
	}
	
	private IPositionService _positionService;
	private ISettingsProvider _settingsProvider;
	
	public ArrayListObservable<GameListItem> Games = new ArrayListObservable<GameListItem>(GameListItem.class);
	public StringObservable Status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(false);
	
	public Command Refresh = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			getGames();
		}
	};
	
	@Inject
	public GameListViewModel(IPositionService positionService, ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionService = positionService;
		_settingsProvider = settingsProvider;
		
		ConnectionManager.registerDataHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
		{
			@Override
			public void handle(GameListResponse data)
			{
				IsWaiting.set(false);
				
				LinkedList<GameListItem> gameList = new LinkedList<GameListItem>();
				
				for (int hostID : data.Games.keySet())
				{
					gameList.add(new GameListItem(hostID, data.Games.get(hostID)));
				}
				
				Games.setArray(gameList.toArray(new GameListItem[0]));
			}
		});
		
		getGames();
	}
	
	private void getGames()
	{
		Status.set(ResourcesProvider.getString(R.string.gameList_waiting));
		IsWaiting.set(true);
		
		_positionService.getSinglePosition(new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				if (position != null)
				{
					ConnectionManager.send(new GameListRequest(position));
				}
				else
				{
					Status.set(ResourcesProvider.getString(R.string.position_fail));
				}
			}
		});
	}
	
	private void joinGame(int hostID, final String hostName)
	{
		Status.set(String.format(ResourcesProvider.getString(R.string.gameList_joining), hostName));
		IsWaiting.set(true);
		
		ConnectionManager.registerDataHandler(JoinGameResponse.class, new IDataHandler<JoinGameResponse>()
		{
			@Override
			public void handle(JoinGameResponse data)
			{
				IsWaiting.set(false);
				
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

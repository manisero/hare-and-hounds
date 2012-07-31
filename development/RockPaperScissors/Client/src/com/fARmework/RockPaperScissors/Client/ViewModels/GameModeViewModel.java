package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Data.ConnectionFaultData;
import com.fARmework.core.client.Data.ConnectionSuccessData;
import com.google.inject.Inject;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class GameModeViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	
	public Command connect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set(ResourcesProvider.get(R.string.connection_connecting));
			ConnectionManager.connect();
		}
	};
	
	public Command create = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ActivitiesManager.startActivity(HostingViewModel.class);
		}
	};
	
	public Command join = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(new GameListRequest());
		}
	};
	
	@Inject
	public GameModeViewModel(IConnectionManager connectionManager, IActivitiesManager activitiesManager)
	{
		super(connectionManager, activitiesManager);
		
		ConnectionManager.registerDataHandler(ConnectionSuccessData.class, new IDataHandler<ConnectionSuccessData>()
		{
			@Override
			public void handle(ConnectionSuccessData data)
			{
				status.set(ResourcesProvider.get(R.string.connection_success));
			}
		});
		
		ConnectionManager.registerDataHandler(ConnectionFaultData.class, new IDataHandler<ConnectionFaultData>()
		{
			@Override
			public void handle(ConnectionFaultData data)
			{
				status.set(ResourcesProvider.get(R.string.connection_fault));
			}
		});
		
		ConnectionManager.registerDataHandler(GameListResponse.class, new IDataHandler<GameListResponse>()
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
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
	}
}

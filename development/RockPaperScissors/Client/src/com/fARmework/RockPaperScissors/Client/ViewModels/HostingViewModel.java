package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.GameJoinRequest;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class HostingViewModel extends ViewModel
{
	public StringObservable status = new StringObservable(ResourcesProvider.get(R.string.hosting_waiting));
	public BooleanObservable isWaiting = new BooleanObservable(true);
	
	@Inject
	public HostingViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		ConnectionManager.registerDataHandler(GameJoinRequest.class, new IDataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(GameJoinRequest data)
			{
				isWaiting.set(false);
				// TODO: allow denying guest
				status.set(ResourcesProvider.get(R.string.hosting_guestConnected));
				ConnectionManager.send(new GameJoinResponse(data.HostID, data.GuestID, GameJoinResponseType.Accept));
				NavigationManager.navigateTo(GameViewModel.class);
			}
		});
	}
}

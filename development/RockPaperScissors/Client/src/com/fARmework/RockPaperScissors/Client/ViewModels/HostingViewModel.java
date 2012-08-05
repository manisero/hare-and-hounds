package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
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
		
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				isWaiting.set(false);
				status.set(ResourcesProvider.get(R.string.hosting_guestConnected));
				NavigationManager.navigateTo(GameViewModel.class);
			}
		});
	}
}

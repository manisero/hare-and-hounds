package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.CreateGameRequest;
import com.fARmework.RockPaperScissors.Data.CreateGameResponse;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class HostingViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	
	public Command create = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Creating game...");
			ConnectionManager.send(new CreateGameRequest());
		}
	};
	
	@Inject
	public HostingViewModel(IConnectionManager connectionManager, IActivitiesManager activitiesManager)
	{
		super(connectionManager, activitiesManager);
		
		ConnectionManager.registerDataHandler(CreateGameResponse.class, new IDataHandler<CreateGameResponse>()
		{
			@Override
			public void handle(CreateGameResponse data)
			{
				status.set(ResourcesProvider.get(R.string.hosting_waiting));
			}
		});
	}
}

package com.fARmework.HareAndHounds.Client.ViewModels;

import gueei.binding.observables.*;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.JoinGameResponseType;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.fARmework.utils.Android.IContextManager.IDialogListener;
import com.google.inject.*;

public class HostingViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(true);
	
	@Inject
	public HostingViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		ConnectionManager.registerDataHandler(NewGameResponse.class, new IDataHandler<NewGameResponse>()
		{
			@Override
			public void handle(NewGameResponse data)
			{
				status.set(ResourcesProvider.getString(R.string.hosting_waiting));
			}
		});
		
		ConnectionManager.registerDataHandler(JoinGameRequest.class, new IDataHandler<JoinGameRequest>()
		{
			@Override
			public void handle(final JoinGameRequest data)
			{
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.hosting_guestConnected), data.GuestName),
					ResourcesProvider.getString(R.string.dialog_yes),
					ResourcesProvider.getString(R.string.dialog_no),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							status.set(String.format(ResourcesProvider.getString(R.string.hosting_guestJoined), data.GuestName));
							
							/*
							ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
							{
								@Override
								public void handle(GameStartInfo info)
								{
									ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
									isWaiting.set(false);
									
									Bundle bundle = new Bundle();
									bundle.putString(GameViewModel.OPPONENT_NAME_KEY, data.GuestUserName);
									ContextManager.navigateTo(GameViewModel.class, bundle);
								}
							});
							*/
							
							ConnectionManager.send(new JoinGameResponse(JoinGameResponseType.Accept, data.GuestID));
						}
					},
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							ConnectionManager.send(new JoinGameResponse(JoinGameResponseType.Reject, data.GuestID));
						}
					});
			}
		});
		
		status.set(ResourcesProvider.getString(R.string.hosting_creating));
		ConnectionManager.send(new NewGameRequest(settingsProvider.getUserName(), new PositionData(0, 0)));
	}
}

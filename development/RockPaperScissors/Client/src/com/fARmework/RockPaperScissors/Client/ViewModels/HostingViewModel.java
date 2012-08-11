package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.ContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Data.GameCreationInfo;
import com.fARmework.RockPaperScissors.Data.GameCreationRequest;
import com.fARmework.RockPaperScissors.Data.GameJoinRequest;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class HostingViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(true);
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public HostingViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		ConnectionManager.registerDataHandler(GameCreationInfo.class, new IDataHandler<GameCreationInfo>()
		{
			@Override
			public void handle(GameCreationInfo data)
			{
				status.set(ResourcesProvider.getString(R.string.hosting_waiting));
			}
		});
		
		ConnectionManager.registerDataHandler(GameJoinRequest.class, new IDataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(final GameJoinRequest data)
			{
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.hosting_guestConnected), data.GuestUserName),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							isWaiting.set(false);
							status.set(String.format(ResourcesProvider.getString(R.string.hosting_guestJoined), data.GuestUserName));
							ConnectionManager.send(new GameJoinResponse(data.GuestID, GameJoinResponseType.Accept));
							
							Bundle bundle = new Bundle();
							bundle.putString(GameViewModel.OPPONENT_NAME_KEY, data.GuestUserName);
							ContextManager.navigateTo(GameViewModel.class, bundle);
						}
					},
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							ConnectionManager.send(new GameJoinResponse(data.GuestID, GameJoinResponseType.Deny));
						}
					});
			}
		});
		
		status.set(ResourcesProvider.getString(R.string.hosting_creating));
		ConnectionManager.send(new GameCreationRequest(_settingsProvider.getUserName()));
	}
}

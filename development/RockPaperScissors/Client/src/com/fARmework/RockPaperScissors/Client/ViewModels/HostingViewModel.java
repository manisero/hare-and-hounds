package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.observables.*;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.Inject;

public class HostingViewModel extends ViewModel
{
	public StringObservable Status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(true);
	
	@Inject
	public HostingViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		ConnectionManager.registerDataHandler(GameCreationResponse.class, new IDataHandler<GameCreationResponse>()
		{
			@Override
			public void handle(GameCreationResponse data)
			{
				Status.set(ResourcesProvider.getString(R.string.hosting_waiting));
			}
		});
		
		ConnectionManager.registerDataHandler(GameJoinRequest.class, new IDataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(final GameJoinRequest data)
			{
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.hosting_guestConnected), data.GuestUserName),
					ResourcesProvider.getString(R.string.dialog_yes),
					ResourcesProvider.getString(R.string.dialog_no),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							Status.set(String.format(ResourcesProvider.getString(R.string.hosting_guestJoined), data.GuestUserName));
							
							ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
							{
								@Override
								public void handle(GameStartInfo info)
								{
									ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
									IsWaiting.set(false);
									
									Bundle bundle = new Bundle();
									bundle.putString(GameViewModel.OPPONENT_NAME_KEY, data.GuestUserName);
									ContextManager.navigateTo(GameViewModel.class, bundle);
								}
							});
							
							ConnectionManager.send(new GameJoinResponse(data.GuestID, GameJoinResponseType.Accept));
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
		
		Status.set(ResourcesProvider.getString(R.string.hosting_creating));
		ConnectionManager.send(new GameCreationRequest(settingsProvider.getUserName()));
	}
}

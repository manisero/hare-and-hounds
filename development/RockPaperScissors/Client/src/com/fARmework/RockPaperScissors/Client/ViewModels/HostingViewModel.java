package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.NavigationManager.IDialogListener;
import com.fARmework.RockPaperScissors.Data.GameJoinRequest;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class HostingViewModel extends ViewModel
{
	public StringObservable status = new StringObservable(ResourcesProvider.getString(R.string.hosting_waiting));
	public BooleanObservable isWaiting = new BooleanObservable(true);
	
	@Inject
	public HostingViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		ConnectionManager.registerDataHandler(GameJoinRequest.class, new IDataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(final GameJoinRequest data)
			{
				NavigationManager.showYesNoDialog(String.format(ResourcesProvider.getString(R.string.hosting_guestConnected), data.GuestUserName),
												  ResourcesProvider.getString(R.string.hosting_allowJoin),
												  ResourcesProvider.getString(R.string.hosting_denyJoin),
												  new IDialogListener()
													{
														@Override
														public void onDialogResult()
														{
															isWaiting.set(false);
															status.set(String.format(ResourcesProvider.getString(R.string.hosting_guestJoined), data.GuestUserName));
															ConnectionManager.send(new GameJoinResponse(data.HostID, data.GuestID, GameJoinResponseType.Accept));
															
															Bundle bundle = new Bundle();
															bundle.putString(GameViewModel.OPPONENT_NAME_KEY, data.GuestUserName);
															NavigationManager.navigateTo(GameViewModel.class, bundle);
														}
													},
												  new IDialogListener()
													{
														@Override
														public void onDialogResult()
														{
															ConnectionManager.send(new GameJoinResponse(data.HostID, data.GuestID, GameJoinResponseType.Deny));
														}
													});
			}
		});
	}
}

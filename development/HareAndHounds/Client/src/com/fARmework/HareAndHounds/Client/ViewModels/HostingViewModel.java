package com.fARmework.HareAndHounds.Client.ViewModels;

import gueei.binding.observables.*;

import android.os.*;

import com.fARmework.HareAndHounds.Client.R;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.JoinGameResponseType;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Android.*;
import com.fARmework.modules.PositionTracking.Android.IPositionService.IPositionListener;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.fARmework.utils.Android.IContextManager.IDialogListener;
import com.google.inject.*;

public class HostingViewModel extends ViewModel
{
	private IPositionService _positionService;
	private ISettingsProvider _settingsProvider;
	
	public StringObservable Status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(true);
	
	@Inject
	public HostingViewModel(IPositionService positionService, ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionService = positionService;
		_settingsProvider = settingsProvider;
		
		ConnectionManager.registerDataHandler(NewGameResponse.class, new IDataHandler<NewGameResponse>()
		{
			@Override
			public void handle(NewGameResponse data)
			{
				Status.set(ResourcesProvider.getString(R.string.hosting_waiting));
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
							Status.set(String.format(ResourcesProvider.getString(R.string.hosting_guestJoined), data.GuestName));
							
							ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
							{
								@Override
								public void handle(GameStartInfo data)
								{
									ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
									IsWaiting.set(false);
									
									Bundle bundle = new Bundle();
									bundle.putInt(HareViewModel.POSITION_UPDATE_INTERVAL_KEY, data.DemandedPositionUpdateInterval);
									ContextManager.navigateTo(HareViewModel.class, bundle);
								}
							});
							
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
		
		Status.set(ResourcesProvider.getString(R.string.hosting_creating));
		
		_positionService.getSinglePosition(new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				if (position != null)
				{
					ConnectionManager.send(new NewGameRequest(_settingsProvider.getUserName(), position));
				}
				else
				{
					Status.set(ResourcesProvider.getString(R.string.position_fail));
				}
			}
		});
	}
}

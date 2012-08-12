package com.fARmework.RockPaperScissors.Client.Infrastructure._impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Activities.GameActivity;
import com.fARmework.RockPaperScissors.Client.Activities.GameListActivity;
import com.fARmework.RockPaperScissors.Client.Activities.GameModeActivity;
import com.fARmework.RockPaperScissors.Client.Activities.HostingActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameListViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameModeViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.HostingViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

@SuppressWarnings("rawtypes")
public class ContextManager implements IContextManager
{
	private Map<Class<? extends ViewModel>, Integer> _layouts = new LinkedHashMap<Class<? extends ViewModel>, Integer>();
	private Map<Class<? extends ViewModel>, Class<? extends BoundActivity>> _activities = new LinkedHashMap<Class<? extends ViewModel>, Class<? extends BoundActivity>>();
	
	private BoundActivity _currentActivity;

	public ContextManager()
	{
		// GameMode
		_layouts.put(GameModeViewModel.class, R.layout.game_mode);
		_activities.put(GameModeViewModel.class, GameModeActivity.class);
		
		// Hosting
		_layouts.put(HostingViewModel.class, R.layout.hosting);
		_activities.put(HostingViewModel.class, HostingActivity.class);
		
		// GameList
		_layouts.put(GameListViewModel.class, R.layout.game_list);
		_activities.put(GameListViewModel.class, GameListActivity.class);
		
		// Game
		_layouts.put(GameViewModel.class, R.layout.game);
		_activities.put(GameViewModel.class, GameActivity.class);
	}
	
	@Override
	public Context getContext()
	{
		return _currentActivity;
	}
	
	@Override
	public int getLayout(Class<? extends ViewModel> viewModelClass)
	{
		return _layouts.get(viewModelClass);
	}
	
	@Override
	public void setCurrentActivity(BoundActivity activity)
	{
		_currentActivity = activity;
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass)
	{
		if (_activities.containsKey(viewModelClass))
		{
			_currentActivity.startActivity(new Intent(_currentActivity, _activities.get(viewModelClass)));
		}
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data)
	{
		if (_activities.containsKey(viewModelClass))
		{
			Intent intent = new Intent(_currentActivity, _activities.get(viewModelClass));
			intent.putExtras(data);
			_currentActivity.startActivity(intent);
		}
	}
	
	@Override
	public void showShortNotification(String notification)
	{
		Toast.makeText(_currentActivity, notification, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void showLongNotification(String notification)
	{
		Toast.makeText(_currentActivity, notification, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void showDialogNotification(String notification, IDialogListener confirmListener)
	{
		showDialogNotification(notification, ResourcesProvider.getString(R.string.dialog_confirm), confirmListener);
	}
	
	@Override
	public void showDialogNotification(String notification, String confirmLabel, final IDialogListener confirmListener)
	{
		new AlertDialog.Builder(_currentActivity)
			.setMessage(notification)
			.setCancelable(false)
			.setPositiveButton(confirmLabel,
							   new OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										if (confirmListener != null)
											confirmListener.onDialogResult();
									}
								})
			.create()
			.show();
	}
	
	@Override
	public void showYesNoDialog(String message, IDialogListener yesListener, IDialogListener noListener)
	{
		showYesNoDialog(message, ResourcesProvider.getString(R.string.dialog_yes), ResourcesProvider.getString(R.string.dialog_no), yesListener, noListener);
	}
	
	@Override
	public void showYesNoDialog(String message, String yesLabel, String noLabel, final IDialogListener yesListener, final IDialogListener noListener)
	{
		new AlertDialog.Builder(_currentActivity)
			.setMessage(message)
			.setCancelable(false)
			.setPositiveButton(yesLabel,
							   new OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										if (yesListener != null)
											yesListener.onDialogResult();
									}
								})
			.setNegativeButton(noLabel,
							   new OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										if (noListener != null)
											noListener.onDialogResult();
									}
								})
			.create()
			.show();
	}
}

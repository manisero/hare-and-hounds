package com.fARmework.utils.Android.Infrastructure._impl;

import java.util.*;

import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

import com.fARmework.utils.Android.Activities.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.*;

@SuppressWarnings("rawtypes")
public class ContextManager implements IContextManager
{
	private final ISettingsProvider _settingsProvider;
	
	private Map<Class<? extends ViewModel>, Class<? extends BoundActivity>> _activities = new LinkedHashMap<Class<? extends ViewModel>, Class<? extends BoundActivity>>();
	private Map<Class<? extends ViewModel>, Integer> _layouts = new LinkedHashMap<Class<? extends ViewModel>, Integer>();
	
	private Stack<BoundActivity> _activitiesStack = new Stack<BoundActivity>();
	
	@Inject
	public ContextManager(ISettingsProvider settingsProvider)
	{
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void registerView(Class<? extends ViewModel> viewModelClass, Class<? extends BoundActivity> activityClass, Integer layoutId)
	{
		_activities.put(viewModelClass, activityClass);
		_layouts.put(viewModelClass, layoutId);
	}
	
	@Override
	public int getLayout(Class<? extends ViewModel> viewModelClass)
	{
		return _layouts.get(viewModelClass);
	}
	
	@Override
	public void onViewStart(BoundActivity activity)
	{
		if (!_activitiesStack.empty() && activity == _activitiesStack.peek())
			return;
		
		_activitiesStack.push(activity);
	}
	
	@Override
	public void onViewStop(BoundActivity activity)
	{
		if (_activitiesStack.empty() || activity != _activitiesStack.peek())
			return;
		
		_activitiesStack.pop();
	}
	
	@Override
	public void finishCurrentView()
	{
		if (!_activitiesStack.empty())
		{
			_activitiesStack.peek().finish();
		}
	}
	
	@Override
	public void finishApplication()
	{
		while (!_activitiesStack.empty())
		{
			_activitiesStack.pop().finish();
		}
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass)
	{
		if (_activitiesStack.empty())
			return;
		
		if (_activities.containsKey(viewModelClass))
		{
			BoundActivity currentActivity = _activitiesStack.peek();
			
			currentActivity.startActivity(new Intent(currentActivity, _activities.get(viewModelClass)));
		}
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data)
	{
		if (_activitiesStack.empty())
			return;
		
		if (_activities.containsKey(viewModelClass))
		{
			BoundActivity currentActivity = _activitiesStack.peek();
			
			Intent intent = new Intent(currentActivity, _activities.get(viewModelClass));
			intent.putExtras(data);
			currentActivity.startActivity(intent);
		}
	}
	
	@Override
	public void showNotification(String notification)
	{
		if (_activitiesStack.empty())
			return;
		
		int duration = (notification.length() <= _settingsProvider.getShortNotificationMaxLength()) ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
		
		Toast.makeText(_activitiesStack.peek(), notification, duration).show();
	}
	
	@Override
	public void showDialogNotification(String notification, String confirmLabel, final IDialogListener confirmListener)
	{
		if (_activitiesStack.empty())
			return;
		
		new AlertDialog.Builder(_activitiesStack.peek())
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
	public void showYesNoDialog(String message, String yesLabel, String noLabel, final IDialogListener yesListener, final IDialogListener noListener)
	{
		if (_activitiesStack.empty())
			return;
		
		new AlertDialog.Builder(_activitiesStack.peek())
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

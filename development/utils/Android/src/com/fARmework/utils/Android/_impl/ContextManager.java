package com.fARmework.utils.Android._impl;

import java.util.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.fARmework.utils.Android.BoundActivity;
import com.fARmework.utils.Android.IContextManager;
import com.fARmework.utils.Android.ViewModel;

@SuppressWarnings("rawtypes")
public class ContextManager implements IContextManager
{
	private Map<Class<? extends ViewModel>, Class<? extends BoundActivity>> _activities = new LinkedHashMap<Class<? extends ViewModel>, Class<? extends BoundActivity>>();
	private Map<Class<? extends ViewModel>, Integer> _layouts = new LinkedHashMap<Class<? extends ViewModel>, Integer>();
	
	private Stack<BoundActivity> _activitiesStack = new Stack<BoundActivity>();
	
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
	public void setCurrentActivity(BoundActivity activity)
	{
		return;
		
		/*
		if (_activitiesStack.size() != 0 && activity == _activitiesStack.peek())
		{
			return;
		}
		
		_activitiesStack.push(activity);
		*/
	}
	
	@Override
	public void finishCurrentActivity()
	{
		_activitiesStack.pop().finish();
	}
	
	@Override
	public void finishApplication()
	{
		while (_activitiesStack.size() != 0)
		{
			_activitiesStack.pop().finish();
		}
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass)
	{
		if (_activities.containsKey(viewModelClass))
		{
			BoundActivity currentActivity = _activitiesStack.peek();
			
			currentActivity.startActivity(new Intent(currentActivity, _activities.get(viewModelClass)));
		}
	}
	
	@Override
	public <T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data)
	{
		if (_activities.containsKey(viewModelClass))
		{
			BoundActivity currentActivity = _activitiesStack.peek();
			
			Intent intent = new Intent(currentActivity, _activities.get(viewModelClass));
			intent.putExtras(data);
			currentActivity.startActivity(intent);
		}
	}
	
	@Override
	public void showShortNotification(String notification)
	{
		Toast.makeText(_activitiesStack.peek(), notification, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void showLongNotification(String notification)
	{
		Toast.makeText(_activitiesStack.peek(), notification, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void showDialogNotification(String notification, String confirmLabel, final IDialogListener confirmListener)
	{
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

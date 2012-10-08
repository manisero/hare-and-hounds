package com.fARmework.utils.Android.Infrastructure;

import com.fARmework.utils.Android.Activities.*;
import com.fARmework.utils.Android.ViewModels.*;

import android.os.Bundle;

@SuppressWarnings("rawtypes")
public interface IContextManager
{
	public interface IDialogListener
	{
		void onDialogResult();
	}
	
	void registerView(Class<? extends ViewModel> viewModelClass, Class<? extends BoundActivity> activityClass, Integer layoutId);
	
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	void setCurrentActivity(BoundActivity activity);
	void finishCurrentActivity();
	void finishApplication();
	
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass);
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data);
	
	void showNotification(String notification);
	void showDialogNotification(String notification, String confirmLabel, IDialogListener confirmListener);
	void showYesNoDialog(String message, String yesLabel, String noLabel, IDialogListener yesListener, IDialogListener noListener);
}

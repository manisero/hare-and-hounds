package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.content.Context;
import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure._impl.ContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

public interface IContextManager
{
	Context getContext();
	
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	@SuppressWarnings("rawtypes")
	void setCurrentActivity(BoundActivity activity);
	
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass);
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data);
	
	void showShortNotification(String notification);
	void showLongNotification(String notification);
	void showDialogNotification(String notification, IDialogListener confirmListener);
	void showDialogNotification(String notification, String confirmLabel, IDialogListener confirmListener);
	void showYesNoDialog(String message, IDialogListener yesListener, IDialogListener noListener);
	void showYesNoDialog(String message, String yesLabel, String noLabel, IDialogListener yesListener, IDialogListener noListener);
}

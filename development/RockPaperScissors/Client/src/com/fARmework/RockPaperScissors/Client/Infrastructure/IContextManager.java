package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.ContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

public interface IContextManager
{
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	@SuppressWarnings("rawtypes")
	void setCurrentActivity(BoundActivity activity);
	
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass);
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data);
	
	void showShortNotification(String notification);
	void showLongNotification(String notification);
	void showYesNoDialog(String message, IDialogListener yesListener, IDialogListener noListener);
	void showYesNoDialog(String message, String yesLabel, String noLabel, IDialogListener yesListener, IDialogListener noListener);
}

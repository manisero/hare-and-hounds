package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.NavigationManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

public interface INavigationManager
{
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	@SuppressWarnings("rawtypes")
	void setCurrentActivity(BoundActivity activity);
	
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass);
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass, Bundle data);
	
	void showShortNotification(String notification);
	void showLongNotification(String notification);
	void showYesNoDialog(String message, String yesLabel, String noLabel, IDialogListener yesListener, IDialogListener noListener);
}

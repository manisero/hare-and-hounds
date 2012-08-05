package com.fARmework.RockPaperScissors.Client.Infrastructure;

import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.NavigationManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

public interface INavigationManager
{
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	@SuppressWarnings("rawtypes")
	void setCurrentActivity(BoundActivity activity);
	
	<T extends ViewModel> void navigateTo(Class<T> viewModelClass);
	
	void showNotification(String notification, boolean longDisplay);
	void showYesNoDialog(String message, String yesLabel, String noLabel, IDialogListener yesListener, IDialogListener noListener);
}

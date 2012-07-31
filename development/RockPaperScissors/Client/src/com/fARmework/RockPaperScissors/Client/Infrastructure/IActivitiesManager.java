package com.fARmework.RockPaperScissors.Client.Infrastructure;

import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

public interface IActivitiesManager
{
	int getLayout(Class<? extends ViewModel> viewModelClass);
	
	@SuppressWarnings("rawtypes")
	void setCurrentActivity(BoundActivity activity);
	
	<T extends ViewModel> void startActivity(Class<T> viewModelClass);
}

package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;
import com.google.inject.Inject;

public class BoundActivity<T extends ViewModel> extends RoboBindingActivity
{
	@Inject
	IActivitiesManager ActivitiesManager;
	
	@Inject
	T viewModel;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivitiesManager.setCurrentActivity(this);
        setAndBindRootView(ActivitiesManager.getLayout(viewModel.getClass()), viewModel);
    }
}

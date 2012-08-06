package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;
import com.google.inject.Inject;

public abstract class BoundActivity<T extends ViewModel> extends RoboBindingActivity
{
	@Inject
	INavigationManager NavigationManager;
	
	@Inject
	T viewModel;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        NavigationManager.setCurrentActivity(this);
        
        viewModel.setData((savedInstanceState != null) ? savedInstanceState : getIntent().getExtras());
        setAndBindRootView(NavigationManager.getLayout(viewModel.getClass()), viewModel);
    }
}

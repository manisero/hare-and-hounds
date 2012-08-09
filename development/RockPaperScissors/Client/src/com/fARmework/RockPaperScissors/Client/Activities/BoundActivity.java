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
        
        if (savedInstanceState != null)
        {
        	viewModel.setData(savedInstanceState);
        }
        else
        {
        	Bundle extras = getIntent().getExtras();
        	
        	if (extras != null)
        		viewModel.setData(extras);
        }
        
        setAndBindRootView(NavigationManager.getLayout(viewModel.getClass()), viewModel);
    }
}

package com.fARmework.utils.Android.Activities;

import android.os.Bundle;
import android.view.*;

import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.Inject;

public abstract class BoundActivity<T extends ViewModel> extends RoboBindingActivity
{
	@Inject
	public IContextManager ContextManager;
	
	@Inject
	public T ViewModel;
	
	protected View View;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if (savedInstanceState != null)
        {
        	ViewModel.initialize(savedInstanceState);
        }
        else
        {
        	Bundle extras = getIntent().getExtras();
        	
        	if (extras != null)
        		ViewModel.initialize(extras);
        }
        
        View = setAndBindRootView(ContextManager.getLayout(ViewModel.getClass()), ViewModel);
    }
	
	@Override
	public void onStart()
	{
    	super.onStart();
    	ContextManager.onViewStart(this);
    	ViewModel.onEntering();
	}
	
	@Override
	public void onStop()
	{
		ViewModel.onLeaving();
		ContextManager.onViewStop(this);
    	super.onStop();
	}
	
	@Override
	public void onBackPressed()
	{
		ViewModel.dispose();
		super.onBackPressed();
	}
	
	@Override
	public void onDestroy()
	{
		ViewModel.dispose();
		super.onDestroy();
	}
}

package com.fARmework.utils.Android;

import android.os.Bundle;
import android.view.*;

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
        ContextManager.setCurrentActivity(this);
        
        if (savedInstanceState != null)
        {
        	ViewModel.setData(savedInstanceState);
        }
        else
        {
        	Bundle extras = getIntent().getExtras();
        	
        	if (extras != null)
        		ViewModel.setData(extras);
        }
        
        View = setAndBindRootView(ContextManager.getLayout(ViewModel.getClass()), ViewModel);
    }
	
	@Override
    public void onBackPressed()
    {
    	ViewModel.onLeaving();
    	super.onBackPressed();
    }
}

package com.fARmework.utils.Android;

import android.os.Bundle;
import com.google.inject.Inject;

public abstract class BoundActivity<T extends ViewModel> extends RoboBindingActivity
{
	@Inject
	IContextManager ContextManager;
	
	@Inject
	T viewModel;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ContextManager.setCurrentActivity(this);
        
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
        
        setAndBindRootView(ContextManager.getLayout(viewModel.getClass()), viewModel);
    }
	
	@Override
    public void onBackPressed()
    {
    	viewModel.onLeaving();
    	super.onBackPressed();
    }
}

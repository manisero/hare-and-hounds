package com.fARmework.client.UI.Activities;

import android.os.Bundle;

import com.fARmework.client.UI.R;
import com.fARmework.client.UI.ViewModels.ClientViewModel;
import com.google.inject.Inject;

public class ClientActivity extends RoboBindingActivity
{
	@Inject ClientViewModel viewModel; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAndBindRootView(R.layout.main, viewModel);
    }
    
    @Override
    public void onBackPressed()
    {
    	viewModel.disconnect();
    	super.onBackPressed();
    }
}
package com.fARmework.client.Activities;

import gueei.binding.app.BindingActivity;
import android.os.Bundle;

import com.fARmework.client.R;
import com.fARmework.client.ViewModels.ClientViewModel;

public class ClientActivity extends BindingActivity
{
	private ClientViewModel _viewModel; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        _viewModel = new ClientViewModel();
        setAndBindRootView(R.layout.main, _viewModel);
    }
    
    @Override
    public void onBackPressed()
    {
    	_viewModel.disconnect();
    	super.onBackPressed();
    }
}
package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ViewModels.MainViewModel;
import com.google.inject.Inject;

public class MainActivity extends RoboBindingActivity
{
	@Inject MainViewModel viewModel; 
	
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
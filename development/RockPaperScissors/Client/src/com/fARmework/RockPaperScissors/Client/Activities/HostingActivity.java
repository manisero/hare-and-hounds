package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ViewModels.HostingViewModel;
import com.google.inject.Inject;

public class HostingActivity extends RoboBindingActivity
{
	@Inject HostingViewModel viewModel; 
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAndBindRootView(R.layout.hosting, viewModel);
    }
}

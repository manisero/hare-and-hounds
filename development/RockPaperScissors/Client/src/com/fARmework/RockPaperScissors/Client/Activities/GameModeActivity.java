package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameModeViewModel;
import com.google.inject.Inject;

public class GameModeActivity extends RoboBindingActivity
{
	@Inject GameModeViewModel viewModel; 
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAndBindRootView(R.layout.game_mode, viewModel);
    }
}
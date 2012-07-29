package com.fARmework.RockPaperScissors.Client.Activities;

import com.fARmework.RockPaperScissors.Client.ViewModels.GameModeViewModel;

public class GameModeActivity extends BoundActivity<GameModeViewModel>
{
	@Override
    public void onBackPressed()
    {
    	viewModel.disconnect();
    	super.onBackPressed();
    }
}
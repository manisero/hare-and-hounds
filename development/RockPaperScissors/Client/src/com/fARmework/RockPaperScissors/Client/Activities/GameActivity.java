package com.fARmework.RockPaperScissors.Client.Activities;

import android.os.*;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameViewModel;
import com.fARmework.modules.SpaceGestures.Android.*;
import com.fARmework.utils.Android.Activities.*;
import com.google.inject.*;

public class GameActivity extends BoundActivity<GameViewModel>
{
	@Inject
	ISpaceGestureRecorder SpaceGestureRecorder;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		((SpaceGesturePicker)findViewById(R.id.spaceGesturePicker)).setSpaceGestureRecorder(SpaceGestureRecorder);
    }
}

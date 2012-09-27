package com.fARmework.HareAndHounds.Client.Activities;

import android.os.*;
import android.view.*;

import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class CheckpointActivity extends BoundActivity<CheckpointViewModel>
{
	@Inject
	IDirectionProvider DirectionProvider;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Set SpaceGraphicsView's DirectionProvider
        //((SpaceGraphicsView)((ViewGroup)View).getChildAt(0)).setDirectionProvider(DirectionProvider); // TODO: Find the view by id once SpaceGraphicsView's constructor is fixed
    }
}
